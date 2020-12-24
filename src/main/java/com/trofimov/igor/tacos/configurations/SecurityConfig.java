package com.trofimov.igor.tacos.configurations;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // private DataSource dataSource;

    // private UserDetailsService userDetailsService;

/*    @Bean
    public PasswordEncoder encode() {
        return new BCryptPasswordEncoder();
    }*/

    @Value("${app.auth.user}")
    private String user;

    @Value("${app.auth.password}")
    private String password;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser(user)
                .password(password)
                .roles("users");
/*                .userDetailsService(userDetailsService)
                .passwordEncoder(encode());*/
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest()
                .access("permitAll")
                .and()
                .httpBasic()
                .and()
                .csrf().disable();

/*                .authorizeRequests()
                .antMatchers("/design", "/orders")
                .access("hasRole('ROLE_USER')")
                .antMatchers("/", "/**")
                .access("permitAll")
                .and()
                .formLogin()
                .loginPage("/login")
                // add true to force to this page in any case
                .defaultSuccessUrl("/design.html")
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .csrf()
                .disable()
        ;*/
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/h2-console/**");
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