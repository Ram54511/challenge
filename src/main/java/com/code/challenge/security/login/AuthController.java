package com.code.challenge.security.login;

import com.code.challenge.common.local.LocalService;
import com.code.challenge.constant.URLConstant;
import com.code.challenge.utils.ErrorCode;
import com.code.challenge.utils.ErrorCollectionUtil;
import com.code.challenge.utils.ResponseCode;
import com.code.challenge.utils.ResponseHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private LocalService localeService;

    @PostMapping(value = URLConstant.LOGIN)
    public ResponseEntity<Object> login(@Valid @RequestBody UserSignInForm userSignInForm, BindingResult bindingResult,
                                        HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            return ResponseHandler.response(HttpStatus.BAD_REQUEST, true,
                    ErrorCollectionUtil.getErrorMessage(bindingResult), ErrorCode.ERROR,
                    ResponseCode.ACKNOWLEDGE_OPTIONAL_RESPONSE_OBJECT, ErrorCollectionUtil.getErrorMap(bindingResult));
        }

        AuthenticationResponseDTO response = authenticationService.authenticate(userSignInForm, request);

        return ResponseHandler.responseWithData(HttpStatus.OK, true, localeService.getMessage("user.login.success"),
                ErrorCode.OK, ResponseCode.ACKNOWLEDGE, response);

    }
}