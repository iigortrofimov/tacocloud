package com.trofimov.igor.tacos.configurations;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.
                jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(
                        "SELECT username, password, enabled from Users where username=?")
                .authoritiesByUsernameQuery(
                        "SELECT username, authority from UserAuthorities where username=?")
                .passwordEncoder(new BCryptPasswordEncoder());


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

    }
}
