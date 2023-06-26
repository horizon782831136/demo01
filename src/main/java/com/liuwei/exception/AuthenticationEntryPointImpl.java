package com.liuwei.exception;

import com.alibaba.fastjson.JSON;
import com.liuwei.utils.Result;
import com.liuwei.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        Result result = new Result(HttpStatus.UNAUTHORIZED.value(), null, "认证失败请重新登录");
        //通过JSON。toJsonString将对象转化为JSON对象
        String json = JSON.toJSONString(result);
        WebUtils.renderString(httpServletResponse, json);
    }
}
