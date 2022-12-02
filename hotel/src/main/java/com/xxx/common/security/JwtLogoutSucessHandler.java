package com.xxx.common.security;
import cn.hutool.json.JSONUtil;
import com.xxx.common.utils.JwtUtils;
import com.xxx.common.utils.Result;
import com.xxx.common.utils.ResultCode;
import com.xxx.common.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class JwtLogoutSucessHandler implements LogoutSuccessHandler {

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if (authentication!=null){
            new SecurityContextLogoutHandler().logout(request,response,authentication);
        }
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream=response.getOutputStream();
        response.setHeader(jwtUtils.getHeard(),"");
        log.info("Exit successfully");
        Result result= ResultUtil.success(ResultCode.SUCCESS);
        outputStream.write(JSONUtil.toJsonStr(result).getBytes("UTF-8"));
        outputStream.flush();
        outputStream.close();
    }
}
