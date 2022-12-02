package com.xxx.common.security;
import com.xxx.common.exception.CaptchaException;
import com.xxx.common.lang.Const;
import com.xxx.common.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class CaptchaFilter extends OncePerRequestFilter {

    private final String loginurl="/login";
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private LoginFailureHandler loginFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Determine whether to log in based on the request path and method
        String url=request.getRequestURI();
        String method = request.getMethod();

        if (url.equals(loginurl) && method.equals("POST")){
            log.info("Got the login link. Verifying the verification code -- " + url);
            try {
                validate(request);
            }catch (CaptchaException e){
                log.info(e.getMessage());
                loginFailureHandler.onAuthenticationFailure(request,response,e);
            }
        }
        filterChain.doFilter(request,response);
    }

    //Verification code verification
    private void validate(HttpServletRequest request) {
        String token = request.getParameter("token");
        String code = request.getParameter("code");

        if (StringUtils.isBlank(code) || StringUtils.isBlank(token)){
            throw new CaptchaException("The verification code cannot be empty");
        }
        if (!code.equals(redisUtil.hget(Const.captcha_KEY,token))){
            throw new CaptchaException("Captcha error");
        }
        //One-time use of verification code
        redisUtil.hdel(Const.captcha_KEY,token);
    }
}
