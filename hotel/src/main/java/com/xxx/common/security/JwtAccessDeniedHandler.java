package com.xxx.common.security;

import cn.hutool.json.JSONUtil;
import com.xxx.common.utils.Result;
import com.xxx.common.utils.ResultCode;
import com.xxx.common.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
        Permission controller
 */
@Slf4j
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream=response.getOutputStream();
        log.info("insufficient privileges");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);

        Result result= ResultUtil.error(ResultCode.PERMISSION_DENIED_ERROR);

        outputStream.write(JSONUtil.toJsonStr(result).getBytes("UTF-8"));
        outputStream.flush();
        outputStream.close();
    }
}
