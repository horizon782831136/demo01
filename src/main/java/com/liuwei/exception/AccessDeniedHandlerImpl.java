package com.liuwei.exception;

import com.alibaba.fastjson.JSON;
import com.liuwei.utils.Result;
import com.liuwei.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        Result result = new Result(HttpStatus.FORBIDDEN.value(), null, "权限不足");
        String json = JSON.toJSONString(result);
        WebUtils.renderString(httpServletResponse, json);
    }
}
