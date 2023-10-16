package com.markhakansson.spacetraderz.app.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class WebSecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Home page
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/home")
                        .permitAll()
                        .anyRequest()
                        .authenticated()
                )
                // Enable basic authentication (for REST calls).
                .httpBasic(Customizer.withDefaults())
                // Enable form login on the website (for website users).
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout(logout -> logout.permitAll())
                .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }


    @Bean
    public UserDetailsService userDetailsService() {
        // TODO Not SAFE, remove default password encoder in the future.
        User.UserBuilder users = User.withDefaultPasswordEncoder();

        UserDetails user = users
                .username("user")
                // TODO remove plaintext password
                .password("password")
                .roles("USER")
                .build();

        UserDetails admin = users
                .username("admin")
                // TODO remove plaintext password
                .password("password")
                .roles("USER, ADMIN")
                .build();

        // TODO change to JDBC later
        return new InMemoryUserDetailsManager(user, admin);
    }
}
