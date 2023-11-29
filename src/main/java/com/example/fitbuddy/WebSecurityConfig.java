package com.example.fitbuddy;

import com.example.fitbuddy.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    private final CustomUserDetailsService userDetailsService;

    @Autowired
    public WebSecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
        requestCache.setMatchingRequestParameterName(null);

        http.csrf((AbstractHttpConfigurer::disable))
                .requestCache((cache) -> cache
                        .requestCache(requestCache)
                )
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(
                                "/",
                                "/css/**",
                                "/assets/**",
                                "/site/**",
                                "/app/signUp",
                                "/app/login",
                                "/app/fogotpassword"
                        ).permitAll()
                        .requestMatchers(
                                "/admin/**"
                        ).hasRole("admin")
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/app/login")
                        .defaultSuccessUrl("/app/dashboard?welcome=true", true)
                        .loginProcessingUrl("/app/login")
                        .failureUrl("/app/login?error=true")
                        .permitAll()
                )
                .logout((logout) -> logout
                        .logoutUrl("/app/logout")
                        .logoutSuccessUrl("/site/")
                        .permitAll()
                );
        return http.build();
    }

    public void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
}
