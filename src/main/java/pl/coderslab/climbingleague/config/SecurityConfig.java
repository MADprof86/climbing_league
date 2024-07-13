package pl.coderslab.climbingleague.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.coderslab.climbingleague.service.LeagueUserDetailService;

@Configuration
@EnableWebSecurity

public class SecurityConfig  {
    @Autowired
    private UserDetailsService userDetailsService;
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())
//                .authorizeRequests(authorizeRequests ->
//                        authorizeRequests.anyRequest().permitAll()
//                )
//                .formLogin(formLogin -> formLogin.disable()
//                )
//                .logout(logout ->logout.disable()
//                );
//
//        return http.build();
//    }
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
            .csrf(csrf -> csrf.disable())
            .authorizeRequests(authorizeRequests ->
                    authorizeRequests
                            .requestMatchers("/admin/**").hasAnyRole("ADMIN", "SUPERADMIN")
                            .requestMatchers("/user/**").hasRole("USER")
                            .anyRequest().permitAll()
            )
            .formLogin(formLogin -> formLogin
                    .loginPage("/login")
                    .defaultSuccessUrl("/", true)
                    .permitAll()
            )
            .logout(logout -> logout
                    .permitAll()
            )
            .exceptionHandling(exceptionHandling ->
                    exceptionHandling.accessDeniedPage("/403")
            );

    return http.build();
}
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public UserDetailsService userDetailsService(){
    return new LeagueUserDetailService();
    }
}
