package com.example.spring_security_demo.config;

import com.example.spring_security_demo.filter.JwtFilter;
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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtFilter jwtFilter;
    @Bean
    public AuthenticationProvider authProvider(){  //authenticate the username and pass if it is exists

        DaoAuthenticationProvider provider= new DaoAuthenticationProvider();  //this DaoAuthProvider connects with the database ,also it extends the class which implements the Authentication provider ,so we will get the Authentication provider obj as op;
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12)); //to encode the password;
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {  //since we have to change the default configuration we have to return the obj of securityfilterchain

        http.csrf(customizer -> customizer.disable())  //disabling csrf
                .authorizeHttpRequests(request -> request
                .requestMatchers("/register","/login")
                .permitAll()        //only request matches with "register" is allowed without authorisation ,every other requests should be authrized..!
                .anyRequest().authenticated())  //all req authenticating
//              .formLogin(Customizer.withDefaults())  //default form login is not required if session is STATELESS
                .httpBasic(Customizer.withDefaults())   //default security settings
                .sessionManagement(session ->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //dont want to maintain session ,for every refresh will be new session..!
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);   //adding filter before the authentication filter to validate the token receiving from req..,before calling the default filter (usernamepasswordFilter we are telling the spring securtiy to call our custom filter(jwtFilter)
        return http.build();
    }

    //since below userDetails service are dealing with static values ,we cannot use that ..!!!!;

//    @Bean
//    public UserDetailsService userDetailsService(){  //spring security uses this class to basically check for your application properties ,to see you have usename and pass there ?,if yes it will use it
//        //so we are defining our own userDetails service
//
//        UserDetails user = User.withDefaultPasswordEncoder()  //hardcoding the user password,username with roles RiyasR
//                               .username("Riyas")
//                               .password("1234")
//                               .roles("USER")
//                               .build();
//
//        UserDetails admin = User.withDefaultPasswordEncoder()
//                .username("Sarath")
//                .password("999")
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager();  //this indirectly returns the userDetailsService (as it extends)
//    }
}
