package com.richieartco.security;

import com.richieartco.controllers.LocalDateTimeFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class EndToEndSecurity implements WebMvcConfigurer {

    private final EndToEndUserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers(
                        "/",
                        "/innoclare",
                        "/innoclare/**",
                        "/home",
                        "/projects",
                        "/categories",
                        "/projects/**",
                        "/project",
                        "/project/**",
                        "/search",
                        "/project/images/",
                        "/project/images/**",
                        "/static/**",
                        "/css/**",
                        "/js/**",
                        "/images/**",
                        "/images/**",
                        "images/**",
                        "/img/**",
                        "/blogs",
                        "/about",
                        "/terms",
                        "/login",
                        "/error",
                        "/register",
                        "/article",
                        "/article/**",
                        "/articles",
                        "/contact",
                        "/users/edit/**",
                        "/api/articles",
                        "/api/articles/**",
                        "/registration/registration-form/**",
                        "/registration/**")
                .permitAll()
                .requestMatchers("/admin").authenticated() // Require ROLE_ADMIN for /admin
         .requestMatchers("/uploadProject").authenticated()
                .requestMatchers("/uploadArticle").authenticated()
                .requestMatchers("/editProject/**").authenticated()
                .requestMatchers("/editOneProject/**").authenticated()
                .requestMatchers("/adminsuccess").authenticated()
                .requestMatchers("/articles/**").authenticated()
                .requestMatchers("/editArticle/**").authenticated()
                .requestMatchers("/editArticle").authenticated()
                .requestMatchers("/editOneArticle").authenticated()
                .requestMatchers("/deleteArticle/**").authenticated()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("email")
                .successHandler(successHandler()) // Set custom success handler
                .permitAll()
                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")
                .and()
                .build();
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return (request, response, authentication) -> {
            if (authentication.getAuthorities().stream().anyMatch(
                    authority -> authority.getAuthority().equals("ROLE_ADMIN")) ) {
                  response.sendRedirect("/admin");
            } else {

                response.sendRedirect("/user");
            }
        };
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new LocalDateTimeFormatter("yyyy-MM-dd HH:mm:ss"));
    }
}
