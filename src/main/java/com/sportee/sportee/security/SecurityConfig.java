package com.sportee.sportee.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
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
//        http
//                .authorizeRequests()
//                    .antMatchers("/","/contact/**","/schedule/**","/trainers/**",
//                            "/js/**","/scss/**", "/fonts/**","/images/**","/css/**","/gym/**",
//                            "/home/**","/members/**", "/measurementTypes/**", "/measurements/**",
//                            "/membership/**", "/timetable/**", "/gymClassTypes/**",
//                            "/subscriptionTypes/**", "/subscriptions/**", "/gymClasses/**",
//                            "/rooms/**","/users/**" ).permitAll()
//                    .anyRequest().authenticated()
//                    .and()
//                .formLogin()
//                    .loginPage("/login")
//                    .permitAll()
//                    .and()
//                .logout()
//                    .permitAll()
//                    .and()
//                .httpBasic();
        http
                .authorizeRequests()
                .antMatchers("/home.html", "/contact/**","/schedule/**","/trainers/**", "/membership/**", "/users/insert").permitAll()
                .antMatchers("/gymClassTypes/**","/subscriptionTypes/**", "/subscriptions/**",
                        "/gymClasses/**","/rooms/**","/users/**", "/measurements/**").authenticated()
                .antMatchers("/gymClassTypes/**","/subscriptionTypes/**", "/subscriptions/**",
                        "/gymClasses/**","/rooms/**","/users/**").hasRole("admin")
                .antMatchers("/measurements/**").hasRole("trainer")
//                .antMatchers("/management/**").hasAnyRole("ADMIN", "MANAGER")
//                .antMatchers("/api/public/users").hasRole("ADMIN")
                .and()
                .formLogin()
                .loginProcessingUrl("/signin")
                .loginPage("/login").permitAll()
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







////    hardcodat ... ar putea fi folosit pentru admin
//    @Bean
//    @Override
//    protected UserDetailsService userDetailsService() {
//        UserDetails user = User.withUsername("admin").password("{noop}pass").roles("ADMIN").build();
//        return new InMemoryUserDetailsManager(user);
//    }


}
