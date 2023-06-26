package com.liuwei.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.liuwei.domain.User;
import com.liuwei.service.UserService;
import com.liuwei.utils.BeanCopyUtils;
import com.liuwei.vo.AddUserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
//1.一定要将类注入容器
@Component
public class UserListener  extends AnalysisEventListener<AddUserVo> {
    //2.声明要注入的service
    @Resource
    private UserService userService;
    //3.这里声明一个临时的静态service类型和上面一样
    public static UserService tempUserService;
    private List<AddUserVo> data = Lists.newArrayList();
    //4.使用PostConstruct来实现初始化(核心)
    @PostConstruct
    public void setTempUserService(){
        tempUserService = this.userService;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(UserListener.class);
    /**
     * 解析每条数据时都会调用
     */
    @Override
    public void invoke(AddUserVo user, AnalysisContext context) {
        data.add(user);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 可以在此处执行业务操作
        //添加到数据库中
        List<User> users = BeanCopyUtils.copyBeanList(data, User.class);
        // 本例就打印到控制台即可，表示读取完成
        users.stream().parallel().forEach(user -> {
            //后面使用临时的静态service对象
            tempUserService.addUser(user);
            //if(!flag) System.out.println("手机号" + user.getTelphone() + "重复！");
        });
        LOGGER.info(JSON.toJSONString(users));
    }
}
