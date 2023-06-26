package com.liuwei.config;

import com.liuwei.filter.AuthenticationTokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 自定义用户认证逻辑
     */
    @Resource
    private UserDetailsService userDetailsService;

    /**
     * 注入passwordEncorder到spring容器
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Resource
    private AuthenticationEntryPoint authenticationEntryPoint;
    @Resource
    private AccessDeniedHandler accessDeniedHandler;

    @Resource
    AuthenticationTokenFilter authenticationTokenFilter;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        //解决frame框架不能嵌套
//        http.headers().frameOptions().disable();
//        //设置没有权限访问跳转自定义页面
//        http.exceptionHandling().accessDeniedPage("/pages/unauth.html");
//        //退出，这里是退出前端接口的约定
//        http.logout().logoutUrl("/login/logout").logoutSuccessUrl("/index.html").permitAll();
        //表单登录
        http.formLogin().disable();
        //都需要身份验证
        http.authorizeRequests()
                //对于登录接口允许匿名访问
                //既可以写接口也可以静态资源
//                .antMatchers("/swagger-ui.html").permitAll()
                //.antMatchers("/login", "/login/register", "/captcha/code").anonymous()
                //其它全部请求需要鉴权认证
                .anyRequest().permitAll();



        //关闭csrf
        http.csrf().disable();


        //把token校验过滤器添加到过滤器中
        http.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        //配置异常处理类
        http.exceptionHandling()
                //配置认证失败处理器
        .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);
    }


    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    /**
     * 身份认证接口
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //告诉SpringSecurity 我们要使用自己定义的userDetailService来通过
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }


}
