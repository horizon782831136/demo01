package com.liuwei.service.impl;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liuwei.dao.UserDao;
import com.liuwei.domain.LoginUser;
import com.liuwei.domain.User;
import com.liuwei.filter.FreezeAndFilter;
import com.liuwei.listener.UserListener;
import com.liuwei.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liuwei.utils.*;
import com.liuwei.vo.AddUserVo;
import com.liuwei.vo.LoginUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;


/**
 * 用户表(User)表服务实现类
 *
 * @author makejava
 * @since 2022-11-05 22:11:29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
    @Resource
    private UserService userService;
    @Resource
    private RedisCache redisCache;
//    @Resource
//    private AuthenticationManager authenticationManager;
    public static AuthenticationManager tempAuthenticationManager;
//    @PostConstruct
//    public void setTemp(){
//        tempAuthenticationManager = this.authenticationManager;
//    }

    @Override
    public Result login(Map<String, String> user, HttpSession session) {
//        try {
////            String captcha = session.getAttribute("captcha").toString();
////            if(!captcha.equalsIgnoreCase(user.get("captcha"))){
////                return new Result(Code.CAPTCHA_ERROR.getKey(), Code.CAPTCHA_ERROR.getMsg());
////            }
//        }catch (Exception e){
//            System.out.println("远程访问导致后端没有收到验证码！");
//        }
//        //暂时
//        //验证权限
//        //设置用户名和密码
//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.get("telphone"), user.get("password"));
//        Authentication authenticate = tempAuthenticationManager.authenticate(authenticationToken);
//        System.out.println(authenticate);
//        if(Objects.isNull(authenticate)){
//            throw new RuntimeException("手机号或密码错误！");
//        }
//        //使用userid生成token
//        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
//        //System.out.println(loginUser);
//        String userId = loginUser.getUser().getUserId().toString();
//        String jwt = JwtUtil.createJWT(userId);
//        //authenticate存入redis
//        redisCache.setCacheObject("login:"+userId,loginUser);
//        //到这里说明验证成功
//
//        //将用户信息存入session中
//        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper();
//        lambdaQueryWrapper.eq(User::getTelphone, user.get("telphone"));
//        User one = userService.getOne(lambdaQueryWrapper);
//        LoginUserVo tempUser = BeanCopyUtils.copyBean(one, LoginUserVo.class);
//        session.setAttribute("user", tempUser);
//        //设置session过期时间
//        session.setMaxInactiveInterval(60 * 60 * 24);//七天
//        //把token响应给前端
//        HashMap<String, String> map = new HashMap<>();
//        System.out.println(jwt);
//        map.put("token", jwt);
//        //将token存入session中
        return new Result(Code.LOGIN_SUCCESS.getKey(), Code.LOGIN_SUCCESS.getMsg());
    }

    @Override
    public Result register(Map<String, String> user, HttpSession session) {
        String captcha = (String) session.getAttribute("captcha");
        //先校验验证码
        if(!session.getAttribute("captcha").toString().equalsIgnoreCase(user.get("captcha"))){
            return new Result(Code.REGISTER_FAILED.getKey(), "验证码错误!");
        }
        User temp = new User();
        temp.setTelphone(user.get("telphone"));
        temp.setPassword(user.get("password"));
        return addTempUser(temp);
    }

    @Override
    public Result addUser(User user) {
        return addTempUser(user);
    }

    @Override
    public Result updateUser(User user) {
        user.setUpdateTime(new Date());
        //对密码进行Bcrypt加密
        //如果不要修改密码就不改密码
        if(Objects.isNull(user.getPassword())){
            boolean flag = userService.updateById(user);
            return ResultUtils.update(flag);
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String password = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(password);
        boolean flag = userService.updateById(user);
        return ResultUtils.update(flag);
    }

    @Override
    public Result deleteById(Long id) {
        boolean flag = userService.removeById(id);
        return ResultUtils.delete(flag);
    }

    @Override
    public Result getByPage(Integer current, Integer size) {
        IPage<User> page = new Page(current, size);
        userService.page(page);
        return new Result(page);
    }

    @Override
    public Result getById(Long id) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUserId, id);
        LoginUserVo loginUserVo = BeanCopyUtils.copyBean(userService.getOne(lambdaQueryWrapper), LoginUserVo.class);
        return new Result(loginUserVo);
    }

    @Override
    public Result logout(HttpSession session) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userid = loginUser.getUser().getUserId();
        redisCache.deleteObject("login:"+userid);
        session.removeAttribute("user");
        return new Result(Code.LOGOUT_SUCCESS.getKey(),  Code.LOGOUT_SUCCESS.getMsg());
    }

    @Override
    public Result getLikeByPage(String tel, Integer current, Integer size) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(User::getTelphone, tel);
        IPage<User> page = new Page<>(current, size);
        userService.page(page, queryWrapper);
        return new Result(page);
    }

    @Override
    public Result getAll() {
        List<LoginUserVo> loginUserVos = BeanCopyUtils.copyBeanList(userService.list(), LoginUserVo.class);
        return new Result(loginUserVos);
    }

    @Override
    public void doDownLoad(HttpServletResponse response) throws IOException {
        ExcelUtils.downLoad(response, AddUserVo.class, BeanCopyUtils.copyBeanList(userService.list(), AddUserVo.class));
    }

    @Override
    public void doUpload(MultipartFile file) throws IOException {
        ExcelUtils.upload(file, AddUserVo.class, new UserListener());
    }

    @Override
    public Result deleteByBatch(List<Long> ids) {
        boolean flag = userService.removeByIds(ids);
        return ResultUtils.delete(flag);
    }

    @Override
    public Result updateBasicInfo(User user) {
        user.setUpdateTime(new Date());
        user.setCreateTime(null);
        boolean flag = userService.updateById(user);
        return ResultUtils.update(flag);
    }

    public Result addTempUser(User user){
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getTelphone, user.getTelphone());
        User one = userService.getOne(lambdaQueryWrapper);
        if(Objects.nonNull(one)){
            return new Result(Code.REGISTER_FAILED.getKey(), "该手机号已被注册，请重新输入密码！");
        }
        //对密码进行Bcrypt加密
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String password = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(password);
        user.setDelFlag(Default.DEFAULT_DELETE);
        user.setRole(Default.DEFAULT_ROLE);
        user.setCreateTime(new Date());
        user.setUpdateTime(null);
        boolean flag = userService.save(user);
        return flag ? new Result(Code.REGISTER_SUCCESS.getKey(), Code.REGISTER_SUCCESS.getMsg()) :
                new Result(Code.REGISTER_FAILED.getKey(), Code.REGISTER_FAILED.getMsg());
    }



    /*
表格样式的设置
    // 背景设置为红色
        headWriteCellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
        WriteFont headWriteFont = new WriteFont();
        headWriteFont.setFontHeightInPoints((short)20);
        headWriteCellStyle.setWriteFont(headWriteFont);
    // 内容的策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
    // 这里需要指定 FillPatternType 为FillPatternType.SOLID_FOREGROUND 不然无法显示背景颜色.头默认了 FillPatternType所以可以不指定
        contentWriteCellStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);
    // 背景绿色
        contentWriteCellStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        WriteFont contentWriteFont = new WriteFont();
    // 字体大小
        contentWriteFont.setFontHeightInPoints((short)20);
        contentWriteCellStyle.setWriteFont(contentWriteFont);

    //设置边框样式
        contentWriteCellStyle.setBorderLeft(BorderStyle.THIN);//细实线
        contentWriteCellStyle.setBorderTop(BorderStyle.THIN);
        contentWriteCellStyle.setBorderRight(BorderStyle.THIN);
        contentWriteCellStyle.setBorderBottom(BorderStyle.THIN);
    // 这个策略是 头是头的样式 内容是内容的样式 其他的策略可以自己实现
    */




    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String pwd = bCryptPasswordEncoder.encode("123");
        System.out.println(pwd);
    }


}
