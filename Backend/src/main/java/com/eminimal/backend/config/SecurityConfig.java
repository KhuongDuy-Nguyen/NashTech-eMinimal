package com.eminimal.backend.config;

import com.eminimal.backend.jwt.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable()
                .authorizeRequests()
//                Anonymous
                .antMatchers(HttpMethod.GET, "/api/category/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/product/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/auth/**").permitAll()
<<<<<<< HEAD
//
////               Role ADMIN
////                .antMatchers(HttpMethod.GET, "/api/**").hasRole("ADMIN")
////                .antMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN")
////                .antMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN")
////                .antMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN")
=======
//               Role ADMIN
//                .antMatchers(HttpMethod.GET, "/api/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN")
>>>>>>> main
////                Role GUEST
////                Cart
//                .antMatchers(HttpMethod.GET, "/api/cart/**").hasAnyRole()
//                .antMatchers(HttpMethod.POST, "/api/cart/**").hasAnyRole()
//                .antMatchers(HttpMethod.PUT, "/api/cart/**").hasAnyRole()
//                .antMatchers(HttpMethod.DELETE, "/api/cart/**").hasAnyRole()
////                manager self-info
<<<<<<< HEAD
//                .antMatchers(HttpMethod.GET, "/api/user/id").hasAnyRole()
//                .antMatchers(HttpMethod.GET, "/api/user/info").hasAnyRole()
//                .antMatchers(HttpMethod.PUT, "/api/user/**").hasAnyRole()



=======
//                .antMatchers(HttpMethod.GET, "/api/user/**").hasAnyRole()
//                .antMatchers(HttpMethod.PUT, "/api/user/**").hasAnyRole()
>>>>>>> main

                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
                .and()
                .logout() // Cho phép logout
                .permitAll();
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
