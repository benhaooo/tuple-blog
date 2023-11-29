package com.hao.handle.security;

import com.alibaba.fastjson.JSON;
import com.hao.domain.ResponseResult;
import com.hao.enums.AppHttpCodeEnum;
import com.hao.utils.WebUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//认证失败处理器
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
//        打印异常
        e.printStackTrace();
        ResponseResult responseResult = null;
//        根据异常的类型判断原因并返回具体的提示信息
        if (e instanceof BadCredentialsException) {
            responseResult = ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_ERROR.getCode(), e.getMessage());//使用异常自带的提示信息(有多种)
        } else if (e instanceof InsufficientAuthenticationException) {
            responseResult = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
        } else {
            responseResult = ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR.getCode(), "认证或授权失败");
        }

//      响应给前端
        WebUtils.renderString(httpServletResponse, JSON.toJSONString(responseResult));
    }
}
