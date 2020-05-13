package com.sportee.sportee;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/","/contact/**","/schedule/**","/trainers/**","/home/**","/js/**","/scss/**",
                        "/fonts/**","/images/**","/css/**","/gym/**","/home/**","/members/**",
                        "/measurementTypes/**", "/measurements/**","/membership/**", "/timetable/**",
                "/gymClassTypes/**", "/subscriptionTypes/**", "/subscriptions/**", "/gymClasses/**","/rooms/**","/users/**" )
                .permitAll()
                .anyRequest().authenticated()
                .and().formLogin().permitAll()
                .and().logout().permitAll();
    }

//    hardcodat ... ar putea fi folosit pentru admin
//    @Bean
//    @Override
//    protected UserDetailsService userDetailsService() {
//        UserDetails user = User.withUsername("memuser").password("{noop}pass").roles("USER").build();
//        return new InMemoryUserDetailsManager(user);
//    }


}
