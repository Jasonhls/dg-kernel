package com.dg.mall.core.config;

import com.dg.mall.core.context.login.LoginContext;
import com.dg.mall.model.api.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Name:
 * Description:
 * Created by helisen on 2019/8/8
 */
@Configuration
public class LoginContextConfiguration {
    @Autowired
    private AuthService authService;
    @Bean
    public LoginContext loginContext(){
        return new LoginContext(authService);
    }
}
