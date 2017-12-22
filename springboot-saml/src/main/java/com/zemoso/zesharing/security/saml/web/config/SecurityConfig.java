package com.zemoso.zesharing.security.saml.web.config;

import com.zemoso.zesharing.security.saml.web.config.jwt.JwtAuthenticationFilter;
import com.zemoso.zesharing.security.saml.web.config.jwt.JwtAuthenticationProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Created by sudheerds on 21/12/17.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Rest security configuration for /api/
     */
    @Configuration
    @Order(1)
    public static class RestApiSecurityConfig extends WebSecurityConfigurerAdapter {

        private static final String API = "/api/**";

        @Override
        protected void configure(HttpSecurity http) throws Exception {

//            http.
//                    sessionManagement()
//                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

            http.addFilterBefore(new JwtAuthenticationFilter(API, super.authenticationManager()), UsernamePasswordAuthenticationFilter.class);

            http.antMatcher(API).authorizeRequests()
                    .anyRequest()
                    .authenticated();
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.authenticationProvider(new JwtAuthenticationProvider());
        }
    }

    /**
     * Saml security config
     */
    @Configuration
    @Import(WebSecurityConfig.class)
    public static class SamlConfig {

    }

}
