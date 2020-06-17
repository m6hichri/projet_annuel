package com.annuel.project.server.entites;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class Users {
    private String login;
    private String firstName;
    private String lastName;
    private String password;
    private String phone;
    private String email;
    private List<String> roles;


    public static String setPasswordCrypt(String password)
    {
        return BCrypt.hashpw(password,BCrypt.gensalt(10));
    }


    public static boolean checkPass(String password,String hashedPassword)
    {
        return BCrypt.checkpw(password, hashedPassword);
    }

}