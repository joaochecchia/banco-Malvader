package com.example.EstudosSpring.User;

import com.example.EstudosSpring.Configurator.TokenConfigurator;
import com.example.EstudosSpring.Response.LoginRequest;

import com.example.EstudosSpring.Response.RegisterRequest;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    public final TokenConfigurator tokenConfigurator;

    public final UserService userService;

    public final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<String> registrar(@RequestBody RegisterRequest request){
        return userService.save(request);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest login){
        UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(login.username(), login.password());
        Authentication authentication = authenticationManager.authenticate(userAndPass);

        UserModel user =(UserModel) authentication.getPrincipal();
        String token = tokenConfigurator.generateToken(user);

        return ResponseEntity.ok(token);
    }
}
