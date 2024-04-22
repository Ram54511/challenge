package com.code.challenge.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
public class UserResponseDto {
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private Date DOB;
}
