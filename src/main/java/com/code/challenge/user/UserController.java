package com.code.challenge.user;

import com.code.challenge.common.local.LocalService;
import com.code.challenge.constant.URLConstant;
import com.code.challenge.user.service.UserService;
import com.code.challenge.utils.ErrorCode;
import com.code.challenge.utils.ErrorCollectionUtil;
import com.code.challenge.utils.ResponseCode;
import com.code.challenge.utils.ResponseHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    private final LocalService localService;

    @PostMapping(value = URLConstant.REGISTER)
    public ResponseEntity<Object> registerUser(@Valid @RequestBody UserRequestDto userRequestDto,
                                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseHandler.response(HttpStatus.BAD_REQUEST, true,
                    ErrorCollectionUtil.getErrorMessage(bindingResult), ErrorCode.ERROR,
                    ResponseCode.ACKNOWLEDGE_OPTIONAL_RESPONSE_OBJECT, ErrorCollectionUtil.getErrorMap(bindingResult));
        }
        User registeredUser = userService.createUser(userRequestDto);
        return ResponseHandler.responseWithData(HttpStatus.OK, true,
                localService.getMessage("user.registration.success"), ErrorCode.OK, ResponseCode.ACKNOWLEDGE, registeredUser);
    }


    @GetMapping(URLConstant.FIND_USERNAME + "{userName}")
    public ResponseEntity<?> getByUsername(String userName) {
        return ResponseHandler.responseWithData(HttpStatus.OK, true,
                localService.getMessage("user.username.success"), ErrorCode.OK, ResponseCode.ACKNOWLEDGE,
                userService.findByUserName(userName));

    }

    @GetMapping(URLConstant.DELETE_BY_ID + "{id}")
    public ResponseEntity<?> deleteById(UUID id) {
        return ResponseHandler.responseWithData(HttpStatus.OK, true,
                localService.getMessage("user.deleted"), ErrorCode.OK, ResponseCode.ACKNOWLEDGE,
                userService.deleteById(id));

    }

    @GetMapping(URLConstant.FIND_LASTNAME + "{lastname}")
    public ResponseEntity<?> getByLastname(String lastname) {
        List<User> userList = userService.findByLastName(lastname);
        return ResponseHandler.responseWithData(HttpStatus.OK, true,
                localService.getMessage("user.lastname.found"), ErrorCode.OK, ResponseCode.ACKNOWLEDGE,
                userList);

    }


    @GetMapping(URLConstant.FIND_EMAIL + "{email}")
    public ResponseEntity<?> getByEmail(String email) {
        return ResponseHandler.responseWithData(HttpStatus.OK, true,
                localService.getMessage("user.email.found"), ErrorCode.OK, ResponseCode.ACKNOWLEDGE,
                userService.findByEmailAddress(email));

    }

    @GetMapping(URLConstant.FIND_FIRSTNAME + "{firstname}")
    public ResponseEntity<?> getByFirstname(String firstname) {
        List<User> userList = userService.findByFirstName(firstname);
        return ResponseHandler.responseWithData(HttpStatus.OK, true,
                localService.getMessage("user.firstname.found"), ErrorCode.OK, ResponseCode.ACKNOWLEDGE,
                userList);

    }


    @PutMapping(value = URLConstant.UPDATE + "{id}")
    public ResponseEntity<Object> updateCategory(@PathVariable("id") UUID id,
                                                 @RequestBody UserRequestDto userRequestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseHandler.response(HttpStatus.BAD_REQUEST, true,
                    ErrorCollectionUtil.getErrorMessage(bindingResult), ErrorCode.ERROR,
                    ResponseCode.ACKNOWLEDGE_OPTIONAL_RESPONSE_OBJECT, ErrorCollectionUtil.getErrorMap(bindingResult));
        }
        User user = userService.updateUser(id, userRequestDto);
        return ResponseHandler.responseWithData(HttpStatus.OK, true, localService.getMessage("user.updated"),
                ErrorCode.OK, ResponseCode.ACKNOWLEDGE, user);
    }


}
