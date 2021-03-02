package com.sportee.sportee.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserPrincipalDetailsService userPrincipalDetailsService;

    public SecurityConfig(UserPrincipalDetailsService userPrincipalDetailsService) {
        this.userPrincipalDetailsService = userPrincipalDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .antMatchers("/home.html", "/contact/**","/schedule/**","/trainers/**", "/login/**", "/users/insert" ).permitAll()
                .antMatchers("/gymClassTypes/**","/subscriptionTypes/**", "/subscriptions/**",
                        "/gymClasses/**","/rooms/**","/users/**", "/measurements/**", "/gymClassBookings/**").authenticated()
                .antMatchers(
                        "/gymClassTypes/**","/subscriptionTypes/**", "/subscriptions/**",
                        "/gymClasses/**","/rooms/**","/users/**").hasRole("admin")
                .antMatchers("/measurements/**").hasRole("trainer")
                .antMatchers( "/gymClassBookings/**").hasAnyRole("trainer", "admin")
                .antMatchers( "/myTimetable/**", "/myAccount/**", "/myProgress/**").hasRole("client")
                .and()
                .formLogin()
                .loginProcessingUrl("/login")
                .loginPage("/login").permitAll()
//                .successHandler(myAuthenticationSuccessHandler())
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login");
//                .and()
//                .rememberMe().tokenValiditySeconds(2592000).key("mySecret!").rememberMeParameter("checkRememberMe");

    }


    DaoAuthenticationProvider authenticationProvider(){
            DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
            daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
            daoAuthenticationProvider.setUserDetailsService(this.userPrincipalDetailsService);
            return daoAuthenticationProvider;
        }


        PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }



}
