package com.example.EstudosSpring.Configurator;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.EstudosSpring.Response.JWTUserResponse;
import com.example.EstudosSpring.User.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TokenConfigurator {
    
    public String generateToken(UserModel user) {
        Algorithm algorithm = Algorithm.HMAC256("secret");

        return JWT.create()
                .withSubject(user.getUsername())
                .withClaim("id", user.getId())
                .withClaim("username", user.getUsername())
                .withClaim("email", user.getEmail())
                .withExpiresAt(Instant.now().plusSeconds(86400))
                .withIssuedAt(Instant.now())
                .sign(algorithm);
    }

    public Optional<JWTUserResponse> validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256("secret");

            DecodedJWT decode =JWT.require(algorithm)
                    .build()
                    .verify(token);

            return Optional.of(JWTUserResponse.builder()
                    .username(decode.getSubject())
                    .id(decode.getClaim("id").asLong())
                    .email(decode.getClaim("email").asString())
                    .build());

        } catch (JWTVerificationException ex){
            return Optional.empty();
        }
    }
}
