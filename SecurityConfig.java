package com.jatan.ems.config;
import com.jatan.ems.service.CustomUserDetailsService;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
@Configuration @EnableMethodSecurity
public class SecurityConfig {
 @Bean PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}
 @Bean SecurityFilterChain security(HttpSecurity http,CustomUserDetailsService users)throws Exception{
  http.userDetailsService(users).authorizeHttpRequests(a->a
   .requestMatchers("/login","/css/**","/js/**","/swagger-ui/**","/v3/api-docs/**","/actuator/health").permitAll()
   .requestMatchers("/employees/new","/employees/*/edit","/employees/*/delete","/employees/save","/employees/*/update").hasAnyRole("ADMIN","HR")
   .requestMatchers("/employees/**","/","/dashboard").authenticated().anyRequest().authenticated())
   .formLogin(f->f.loginPage("/login").defaultSuccessUrl("/dashboard",true).permitAll())
   .logout(l->l.logoutSuccessUrl("/login?logout").permitAll())
   .exceptionHandling(e->e.accessDeniedPage("/access-denied"));
  return http.build();
 }
}
