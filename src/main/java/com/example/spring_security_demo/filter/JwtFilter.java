package com.example.spring_security_demo.filter;

import com.example.spring_security_demo.service.JwtService;
import com.example.spring_security_demo.service.MyUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.context.SecurityContext;

import java.io.IOException;

//this is the class where we verify the jwtoken and the token will be recieved from HttpServletReq req ..!!

//since the extended class wll be called once per every request..!!
@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    JwtService jwtService;

    @Autowired
    ApplicationContext context;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");  //getting the authorization header from the Bearer Token

        String token = null;
        String userName = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")){
            token = authHeader.substring(7);   //count of bearer and space ("Bearer ")
            userName = jwtService.extractUserName(token); }

            //if there is already an authentication object available ,no need to check once again ,ie so if the authentication obj is null only we need to do the validation
            if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null){
            //here getting the obj of MyUserDetailsService and calling the method called loadUserByUsername and we will get the UserDetails
                UserDetails userDetails = context.getBean(MyUserDetailsService.class).loadUserByUsername(userName);


                //method for validating the token with userDetails
                //once the below method is valid we create a token for authentication
                if (jwtService.validateToken(token, userDetails)){

                    //here we created a new token for UsernamePasswordAuthenticationFilter (Spring inbuild fitler) based on our custom jwtFilter
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    //the new authToken which only has the details about the user ,but what exactly request was ,the authToken has no idea,
                    //to do that we have to setting the details below ,ie the token should know the request
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    //and we have to set the token in SecurityContext
                    SecurityContextHolder.getContext().setAuthentication(authToken);

                    //so basically we are created a new token which is authToken and we have added that into SecurityContext
                }
            }
            //this method forward the token into next filter,so by doing this we are continuning the filter chain..
            filterChain.doFilter(request,response);
    }

}


