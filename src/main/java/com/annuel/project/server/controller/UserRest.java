package com.annuel.project.server.controller;


import com.annuel.project.server.entites.Account;
import com.annuel.project.server.entites.Users;
import com.annuel.project.server.firebase.FirebaseService;
import com.annuel.project.server.security.TokenProvider;
import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.concurrent.ExecutionException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;


@RestController
public class UserRest {

       private final FirebaseService firebaseService;
       private final TokenProvider tokenProvider;
       private final AuthenticationManagerBuilder authenticationManager;

    public UserRest(FirebaseService firebaseService, TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManager) {
        this.firebaseService = firebaseService;
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/createAccount")
       public String createAccount(@RequestBody Users user) throws ExecutionException, InterruptedException {
                firebaseService.saveUser(user);

            return "Successfully created new user";
        }


        @GetMapping("/detailUser")
        public Users getUser(@RequestParam String login) throws ExecutionException, InterruptedException {
            return firebaseService.getUserDetail(login);
        }

        @PutMapping("/updateUser")
        public String updateUser(@RequestBody Users user) throws ExecutionException, InterruptedException {
            return firebaseService.updateUserDetails(user);
        }


        @DeleteMapping("/deleteUser")
        public String deleteUser(@RequestParam String login) throws FirebaseAuthException {
            return firebaseService.deleteUser(login);
        }


        @PostMapping("/login")
        public ResponseEntity<?> loginUser(@RequestBody Users user) throws ExecutionException, InterruptedException {
            //return firebaseService.connectAccount(account);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword());

            Authentication authentication = authenticationManager.getObject().authenticate(authenticationToken);

            String token = tokenProvider.createToken(authentication);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(AUTHORIZATION, "Bearer " + token);

            return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
        }




}

