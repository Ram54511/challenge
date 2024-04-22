package com.code.challenge.security.login;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSignInForm {
    @NotBlank(message = "{user.login.email.required}")
    private String email;

    @NotBlank(message = "{user.login.password.required}")
    private String password;
}
