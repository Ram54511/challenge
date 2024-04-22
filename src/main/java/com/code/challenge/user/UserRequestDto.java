package com.code.challenge.user;

import com.code.challenge.common.validation.AgeNotFuture;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
public class UserRequestDto {
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    @Enumerated(EnumType.STRING)
    private UserType userType;

    private String password;

//    @AgeNotFuture
    private Date DOB;
}
