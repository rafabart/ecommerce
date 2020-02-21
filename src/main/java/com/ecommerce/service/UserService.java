package com.ecommerce.service;

import com.ecommerce.security.UserSpringSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

public class UserService {

    public static UserSpringSecurity authenticated() {

        try {

            return (UserSpringSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        } catch (Exception e) {
            return null;
        }
    }
}
