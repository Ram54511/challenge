package com.code.challenge.security.login;

import com.code.challenge.common.exception.CustomException;
import com.code.challenge.common.local.LocalService;
import com.code.challenge.security.factory.JWTTokenFactory;
import com.code.challenge.user.User;
import com.code.challenge.user.service.UserService;
import com.code.challenge.utils.PasswordEncoderUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserService userService;

    private final LocalService localeService;

    private final JWTTokenFactory jwtTokenFactory;

    private final PasswordEncoderUtil passwordEncoderUtil;


    public AuthenticationResponseDTO authenticate(UserSignInForm userSignInForm, HttpServletRequest request) {
        User user = validateUserCredentialsAndGetUser(userSignInForm);
        JWTAuthenticationResponse jwtAuthenticationData = createAuthenticationData(user.getEmail());
        return buildAuthenticationResponse(jwtAuthenticationData, user);
    }

    private User validateUserCredentialsAndGetUser(UserSignInForm userSignInForm) {
        String email = userSignInForm.getEmail().strip();
        Optional<User> optionalUser = userService.findByEmailAddress(email);
        if (optionalUser.isEmpty()) {
            optionalUser = userService.findByUserName(email);
        }
        if (optionalUser.isEmpty() || optionalUser.isPresent()
                && !passwordEncoderUtil.matches(userSignInForm.getPassword(), optionalUser.get().getPassword())) {
            throw new CustomException(localeService.getMessage("user.login.credentials.invalid"),
                    HttpStatus.BAD_REQUEST);
        }
        return optionalUser.get();
    }


    private JWTAuthenticationResponse createAuthenticationData(String email) {
        return jwtTokenFactory.createTokenPair(email);
    }

    private AuthenticationResponseDTO buildAuthenticationResponse(JWTAuthenticationResponse jwtAuthenticationData,
                                                                  User user) {
        return new AuthenticationResponseDTO(jwtAuthenticationData.getAccessToken(),
                jwtAuthenticationData.getRefreshToken(), user.getFirstName()+user.getLastName());
    }
}
