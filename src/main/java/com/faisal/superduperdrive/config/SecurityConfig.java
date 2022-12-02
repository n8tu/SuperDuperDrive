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

        http.authorizeRequests()
                .antMatchers("/css/*","/js/*")
                .permitAll()

                .and()

                .authorizeRequests()
                .antMatchers("/login","/signup")
                .not()
                .authenticated()

                .and()

                .authorizeRequests()
                .antMatchers("/","/*","/**")
                .authenticated();



        http.formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/");

        http.csrf().disable();
        http.headers().frameOptions().disable();

    }

}
