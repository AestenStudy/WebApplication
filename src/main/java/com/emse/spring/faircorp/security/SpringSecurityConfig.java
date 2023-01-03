//package com.emse.spring.faircorp.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//import static org.springframework.security.config.Customizer.withDefaults;
//
//@Configuration
//@EnableWebSecurity
//public class SpringSecurityConfig {
//
//    private static final String ROLE_USER = "USER";
//    private static final String ROLE_ADMIN = "ADMIN";
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        // We create a password encoder
//        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(
//                User.withUsername("user").password(encoder.encode("user")).roles(ROLE_USER).build()
//        );
//        manager.createUser(
//                User.withUsername("admin").password(encoder.encode("admin")).roles(ROLE_ADMIN).build()
//        );
//        return manager;
//    }
//
//    @Bean
//    @Order(2)
//    public SecurityFilterChain filterChainAnyAll(HttpSecurity http) throws Exception {
//        return http.cors().and().csrf().disable()
//                .authorizeRequests(authorize -> authorize.anyRequest().authenticated())
//                .formLogin(withDefaults())
//                .httpBasic(withDefaults())
//                .build();
//    }
//
//    @Bean
//    @Order(1)
//    public SecurityFilterChain filterChainApiAdmin(HttpSecurity http) throws Exception {
//        return http.cors().and().csrf().disable()
//                .antMatcher("/api/**")
//                .authorizeRequests(authorize -> authorize.anyRequest().hasRole(ROLE_ADMIN))
//                .formLogin(withDefaults())
//                .httpBasic(withDefaults())
//                .build();
//    }
//}
