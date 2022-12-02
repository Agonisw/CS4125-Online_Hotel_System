package com.xxx.common.config;

import com.xxx.common.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Security core configure
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
//开启基于方法的安全认证机制，也就是说在web层的controller启用注解机制的安全确认
//只有加了@EnableGlobalMethodSecurity(prePostEnabled=true) 那么在上面使用的 @PreAuthorize(“hasAuthority(‘admin’)”)才会生效
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    LoginSuccessHandler loginSuccessHandler;
    @Autowired
    LoginFailureHandler loginFailureHandler;
    @Autowired
    CaptchaFilter captchaFilter;
    @Autowired
    JwtAccessDeniedHandler jwtAccessDeniedHandler;
    @Autowired
    JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @Autowired
    UserDetalisServiceImpl userDetalisService;
    @Autowired
    JwtLogoutSucessHandler jwtLogoutSucessHandler;

    @Bean
    JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        JwtAuthenticationFilter jwtAuthenticationFilter=new JwtAuthenticationFilter(authenticationManager());
        return jwtAuthenticationFilter;
    }
    //Configuring the Encryption Mode
    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    private static String[] URL_WHITELIST= {
            "/security/login",
            "/security/user/register",
            "/security/api/oss/policy",
            "/bindingRecord",
            "/logout",
            "/captcha",
            "/favicon.ico",
            "/doc.html",
            "/v2/api-docs", "/swagger-resources/configuration/ui",
            "/swagger-resources", "/swagger-resources/configuration/security",
            "/swagger-ui.html", "/webjars/**","/v3/api-docs",
            "/back/material/*",
            "/security/material/exportMaterialExcel",

            "/security/material/importXls"
    };

    //Configure HTTP-related security
    protected void configure(HttpSecurity http) throws Exception {

        //Allow to cross
        http.cors().and()
        //Turn off Prevention Attacks //Disable the enabled csrf protectio
        .csrf().disable()

        //Login Configure
        .formLogin()
                .loginProcessingUrl("/security/login")
                //Configure the authentication success processor
                .successHandler(loginSuccessHandler)
                //Configure the authentication failure handler
                .failureHandler(loginFailureHandler)
        //Login and Exit Configuration
        .and()
        .logout()
        .logoutSuccessHandler(jwtLogoutSucessHandler)

           /*
            always：保存session状态
            never：不会创建HttpSession，但是会使用已经存在的HttpSession
            if_required：仅在需要HttpSession创建
            stateless：不会保存session状态
            */
        //Forbidden session
                .and()
                .sessionManagement()
                 .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        //Configuring Interception Rules
        .and()
        .authorizeRequests()
        .antMatchers(URL_WHITELIST).permitAll() //白名单
        .anyRequest().authenticated() //其他访问路径都需要登录操作

        //Configuring exception handlers
        .and()
        .exceptionHandling()
        .authenticationEntryPoint(jwtAuthenticationEntryPoint)  //认证入口点 用户请求处理过程中遇到认证异常
        .accessDeniedHandler(jwtAccessDeniedHandler)       //权限不足处理器

        //Configure a custom filter
        //Set the CAPTCHA filter before logging in with the username
        .and()
        .addFilter(jwtAuthenticationFilter())


;
   }
   //Configure service


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(userDetalisService);
    }
}
