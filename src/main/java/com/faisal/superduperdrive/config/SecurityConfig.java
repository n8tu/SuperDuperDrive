package com.faisal.superduperdrive.config;

import com.faisal.superduperdrive.services.AuthenticationService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticationService authenticationService;


    public SecurityConfig(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
     protected void configure(AuthenticationManagerBuilder auth){
        auth.authenticationProvider(this.authenticationService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // Allow authenticated users requests to visit these urls
        http.authorizeRequests()
                .antMatchers("/").authenticated();

        // Reject authenticated users requests to visit these urls
        http.authorizeRequests()
                        .antMatchers("/login","/signup").not().authenticated();

        // Allow all visitors to requests to access all remaining urls
        http.authorizeRequests()
                        .anyRequest().permitAll();

        http.formLogin()
                .loginPage("/login");

        http.formLogin()
                .defaultSuccessUrl("/");

        http.csrf().disable();
        http.headers().frameOptions().disable();
    }
}
