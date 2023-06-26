package com.liuwei.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.liuwei.domain.LoginUser;
import com.liuwei.domain.User;
import com.liuwei.service.UserService;
import com.liuwei.utils.*;
import com.liuwei.vo.LoginUserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
@RestController
@RequestMapping("/login")
public class LoginController {
    @Resource
    private UserService userService;
    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private RedisCache redisCache;
    @PostMapping
    public Result login(@RequestBody Map<String, String> user, HttpSession session){
        try {
            String captcha = session.getAttribute("captcha").toString();
            if(!captcha.equalsIgnoreCase(user.get("captcha"))){
                return new Result(Code.CAPTCHA_ERROR.getKey(), Code.CAPTCHA_ERROR.getMsg());
            }
        }catch (Exception e){
            System.out.println("远程访问导致后端没有收到验证码！");
        }
        //暂时
        //验证权限
        //设置用户名和密码
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.get("telphone"), user.get("password"));
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        System.out.println(authenticate);
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("手机号或密码错误！");
        }
        //使用userid生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        //System.out.println(loginUser);
        String userId = loginUser.getUser().getUserId().toString();
        String jwt = JwtUtil.createJWT(userId);
        //authenticate存入redis
        redisCache.setCacheObject("login:"+userId,loginUser);
        //到这里说明验证成功

        //将用户信息存入session中
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(User::getTelphone, user.get("telphone"));
        User one = userService.getOne(lambdaQueryWrapper);
        LoginUserVo tempUser = BeanCopyUtils.copyBean(one, LoginUserVo.class);
        session.setAttribute("user", tempUser);
        //设置session过期时间
        session.setMaxInactiveInterval(60 * 60 * 24);//七天
        //把token响应给前端
        HashMap<String, String> map = new HashMap<>();
        System.out.println(jwt);
        map.put("token", jwt);
        //将token存入session中
        return new Result(Code.LOGIN_SUCCESS.getKey(), map, Code.LOGIN_SUCCESS.getMsg());
    }

    @PostMapping("/register")
    public Result register(@RequestBody Map<String, String> user, HttpSession session) {
        return userService.register(user, session);
    }
    @GetMapping("/fresh")
    @PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_user')")
    public Result fresh(HttpSession session) {
        LoginUserVo user = (LoginUserVo)session.getAttribute("user");
        return new Result(user);
    }

    @GetMapping("/logout")
    public Result logout(HttpSession session){
        return userService.logout(session);
    }


}
