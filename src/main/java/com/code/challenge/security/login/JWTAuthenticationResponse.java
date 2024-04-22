package com.code.challenge.security.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JWTAuthenticationResponse {
    private String accessToken;
    private String refreshToken;
}