package com.company.config;

import com.company.security.JwtConfigurer;
import com.company.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/users/**").permitAll()
                .antMatchers("/departments/add-department").hasRole("USER")
                .antMatchers("/departments/patch-department").hasRole("USER")
                .antMatchers("/departments-employees/add-department-employee").hasRole("USER")
                .antMatchers("/departments-employees/patch-department-employee").hasRole("USER")
                .antMatchers("/employees/add-employee").hasRole("USER")
                .antMatchers("/employees/patch-employee").hasRole("USER")
                .antMatchers("/projects/add-project").hasRole("USER")
                .antMatchers("/projects/patch-project").hasRole("USER")
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));
    }
}
