package com.userexample.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.servlet.DispatcherType;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig{

    @Autowired
    ServiceUserDetailService serviceUserDetailService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new SimplePasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .cors().disable()
            .authorizeHttpRequests(request -> request
                    //.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                    .antMatchers("/checkUser").permitAll()
                    .anyRequest().authenticated()
            )
            .formLogin(login -> login
                    .loginPage("/login")
                    .loginProcessingUrl("/login_process")
                    .defaultSuccessUrl("/", true)
                    .permitAll()
            )
            .logout(withDefaults())
                .sessionManagement()
                .sessionFixation().changeSessionId()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(false)
                .expiredUrl("/login")
                .sessionRegistry(sessionRegistry());

        http.rememberMe() // remember me 기능 추가 명시
                .rememberMeParameter("rememberMe") // login page 의 checkbox name과 동일하게 맞추기
                .tokenValiditySeconds(360) // 만료시간 설정 s(초)
                .alwaysRemember(false) // 사용자가 체크박스를 활성화하지 않아도 항상 실행, default: false
                .userDetailsService(serviceUserDetailService); // 사용자 정보 조회를 위한 userDetailService 주입
        return http.build();
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }
}
