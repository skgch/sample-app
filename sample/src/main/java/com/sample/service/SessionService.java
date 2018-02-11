package com.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public void autoLogin(String username, String password) {
        UserDetails userDetails = userService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken token
            = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        Authentication authentication = authenticationManager.authenticate(token);

        if (authentication.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }

}
