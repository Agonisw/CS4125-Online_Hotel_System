package com.xxx.common.security;

import cn.hutool.core.util.StrUtil;
import com.xxx.common.entity.User;
import com.xxx.common.service.UserService;
import com.xxx.common.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
    Customize a filter to identify jwt
    获Access to the username after we encapsulated into a UsernamePasswordAuthenticationToken directly,
    The authentication object is passed to the SecurityContextHolder parameter, so that security can obtain the information of the current login user and complete user authentication.
 */
@Slf4j
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserDetalisServiceImpl userDetalisService;
    @Autowired
    private UserService userService;
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("jwt verify filter");
        String jwt=request.getHeader(jwtUtils.getHeard());
        if (StrUtil.isBlankOrUndefined(jwt)){
            chain.doFilter(request,response);
            return;
        }
        Claims claims=jwtUtils.getClaimsByToken(jwt);
        if (claims ==null){

            new JwtException("token 异常");
        }
        if (jwtUtils.isTokenExpried(claims)){
            new JwtException("token 已过期");
        }
        String username = claims.getSubject();
        String id = claims.getId();
        log.info("User-{}，Login！", username);
        User user =userService.getUserByUsername(username);
        //Obtain user permissions and other information
        UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken(username,null,userDetalisService.getUserAuthority(user.getId()));
        // The authentication object is passed to the SecurityContextHolder parameter, so that security can obtain the information of the current login user and complete user authentication.
        token.setDetails(id+"");
        SecurityContextHolder.getContext().setAuthentication(token);
        chain.doFilter(request,response);
    }
}
