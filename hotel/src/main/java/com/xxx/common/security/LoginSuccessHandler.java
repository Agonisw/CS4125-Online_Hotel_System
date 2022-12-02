package com.xxx.common.security;

import cn.hutool.json.JSONUtil;
import com.xxx.common.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private JwtUtils jwtUtils;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream=response.getOutputStream();
        AccountUser accountUser = (AccountUser)authentication.getPrincipal();
        System.out.println(accountUser.toString());
        System.out.println(accountUser.getUserId());
        String jwt= jwtUtils.generateToken(authentication.getName(),accountUser.getUserId());
        Collection<? extends GrantedAuthority> authorities = accountUser.getAuthorities();
        System.out.println("----------");
        System.out.println(authorities);
        System.out.println("======");

        response.setHeader(jwtUtils.getHeard(),jwt);
        Result result= ResultUtil.success(ResultCode.LOGIN_SUCCESS);
        Map<String,Object> map = new HashMap<>();
        map.put("code",1000);
        map.put("msg","Authentication succeeded 11");
        map.put("userId",accountUser.getUserId());
        map.put("userType",accountUser.getUserType());
        System.out.println(accountUser.toString());
        System.out.println(accountUser.getFirstRouter());
        map.put("firstRouter",accountUser.getFirstRouter());
        log.info("Authentication succeeded");
        outputStream.write(JSONUtil.toJsonStr(map).getBytes("UTF-8"));
        outputStream.flush();
        outputStream.close();
    }
}
