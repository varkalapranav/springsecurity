package com.example.securityapplication.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity //it disables the default configirations
// it bypasses the filters as of now all the configurations has to be made by me
public class SecurityConfig {

    @Autowired
    UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


//        http.csrf(customizer ->customizer.disable());
//        http.authorizeHttpRequests(request-> request.anyRequest().authenticated());
//        http.formLogin(Customizer.withDefaults());
//        http.httpBasic(Customizer.withDefaults());
//        http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));


        //builder pattern
        http
                .csrf(customizer ->customizer.disable())
                .authorizeHttpRequests(request-> request
                        .requestMatchers("user/login","user/register")
                        .permitAll()
                        .anyRequest().authenticated())
//                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails user1= User.withDefaultPasswordEncoder()
//                .username("chinna")
//                .password("c@123")
//                .roles("ADMIN")
//                .build();
//        UserDetails user2= User.withDefaultPasswordEncoder()
//                .username("harsh")
//                .password("h@123")
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(user1,user2);
//    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider =new DaoAuthenticationProvider();
//        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance()); //without the encoding the password
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12)); //with checking the entered password
        provider.setUserDetailsService(userDetailsService);

        return  provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {

        return config.getAuthenticationManager();

    }

}
