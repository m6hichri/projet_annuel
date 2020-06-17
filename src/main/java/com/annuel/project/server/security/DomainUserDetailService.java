package com.annuel.project.server.security;

import com.annuel.project.server.entites.Users;
import com.annuel.project.server.firebase.FirebaseService;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class DomainUserDetailService implements UserDetailsService {

    private final FirebaseService firebaseService;

    public DomainUserDetailService(FirebaseService firebaseService) {
        this.firebaseService = firebaseService;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        try {

            Users appUser = firebaseService.getUserDetail(login);


            UserDetails u = User.withUsername(login)
                    .password(appUser.getPassword())
                    .authorities(new ArrayList<>())
                    .accountExpired(false)
                    .accountLocked(false)
                    .credentialsExpired(false)
                    .disabled(false)
                    .build();

            return u;

        }catch (Exception e){
            throw new AuthenticationServiceException("Login " + login + " not found");
        }


    }
}