package com.ecommerce.resource;

import com.ecommerce.entity.dto.EmailDTO;
import com.ecommerce.security.JWTUtil;
import com.ecommerce.security.UserSpringSecurity;
import com.ecommerce.service.AuthService;
import com.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthResource {

    final private JWTUtil jwtUtil;

    final private AuthService authService;

    @Autowired
    public AuthResource(final JWTUtil jwtUtil, final AuthService authService) {
        this.jwtUtil = jwtUtil;
        this.authService = authService;
    }


    @PostMapping("/refresh_token")
    public ResponseEntity<Void> refreshToken(HttpServletResponse response) {

        final UserSpringSecurity user = UserService.authenticated();

        String token = jwtUtil.generateToken(user.getUsername());

        response.addHeader("Authorization", "Bearer " + token);
        response.addHeader("access-control-expose-headers", "Authorization");

        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/forgot", consumes = {"application/json"})
    public ResponseEntity<Void> forgotPassword(@Valid @RequestBody final EmailDTO emailDTO) {

        authService.sendNewPassword(emailDTO.getEmail());

        return ResponseEntity.noContent().build();
    }
}
