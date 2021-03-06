package com.trofimov.igor.tacos.configurations;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // private DataSource dataSource;

    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder encode() {
        return new BCryptPasswordEncoder();
    }
/*
    @Value("${app.auth.user}")
    private String user;

    @Value("${app.auth.password}")
    private String password;*/


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(encode());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/design/**", "/orders/**")
                .permitAll()
               //permit only for test
                // .access("hasRole('USER')")
                .antMatchers("/", "/register", "/api/**")
                .access("permitAll")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                // add true to force to this page in any case
                .defaultSuccessUrl("/design/recent", true)
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .csrf()
                .disable()
        ;
        /*                authorizeRequests().anyRequest()
                .access("permitAll")
                .and()
                .httpBasic()
                .and()
                .csrf().disable();*/
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/h2-console/**", "/css/**", "/images/**");
    }
}


//with jdbc
/*        auth.
                jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(
                        "SELECT username, password, enabled from Users where username=?")
                .authoritiesByUsernameQuery(
                        "SELECT username, authority from UserAuthorities where username=?")
                .passwordEncoder(new BCryptPasswordEncoder());*/


// in-memory
       /* auth
                .inMemoryAuthentication()
                .withUser("BUZZ")
                    .password("password")
                    .authorities("ROLE_USER")
                .and()
                .withUser("WOODY")
                    .password("password")
                    .authorities("ROLE_USER");*/