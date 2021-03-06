package com.sample.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.sample.service.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/img/**",
                "/css/**",
                "/js/**",
                "/bootstrap/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/signup", "/login", "/help", "/about", "/contact").permitAll()
                .anyRequest().authenticated();

        http
                .formLogin()
                .loginProcessingUrl("/login")
                .loginPage("/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .failureUrl("/login?error")
                .defaultSuccessUrl("/default")
                .permitAll()
                .and()
                .rememberMe()
                .useSecureCookie(true);

        http
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout**"))
                .logoutSuccessUrl("/login");

        http
                .exceptionHandling().accessDeniedPage("/");
    }

    @Configuration
    protected static class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {
        @Autowired
        UserService userService;

        @Override
        public void init(AuthenticationManagerBuilder auth) throws Exception {
            auth
                    .userDetailsService(userService)
                    .passwordEncoder(new BCryptPasswordEncoder());
        }
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}