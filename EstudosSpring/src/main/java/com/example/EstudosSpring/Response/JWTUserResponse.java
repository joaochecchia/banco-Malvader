package com.example.EstudosSpring.Response;

import lombok.Builder;

@Builder
public record JWTUserResponse (Long id, String username, String email){
}
