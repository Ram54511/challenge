package com.code.challenge.user.service;

import com.code.challenge.common.exception.CustomException;
import com.code.challenge.common.local.LocalService;
import com.code.challenge.user.User;
import com.code.challenge.user.UserRepository;
import com.code.challenge.user.UserRequestDto;
import com.code.challenge.utils.PasswordEncoderUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Ram Thapa
 */

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final LocalService localService;

    private final PasswordEncoderUtil passwordEncoderUtil;


    /**
     * Creates a new user based on the provided user request details.
     *
     * @param requestDto User data containing information for creating a new user.
     * @return The newly created User object.
     */
    @Override
    public User createUser(UserRequestDto requestDto) {
        return userRepository.save(User.builder()
                .firstName(requestDto.getFirstName())
                .lastName(requestDto.getLastName())
                .email(requestDto.getEmail())
                .DOB(requestDto.getDOB())
                .userName(requestDto.getUserName())
                .userType(requestDto.getUserType())
                .password(passwordEncoderUtil.encode(requestDto.getPassword()))
                .build());

    }


    /**
     * Updates an existing user based on the provided ID and user request details.
     *
     * @param id             The unique identifier of the user to update.
     * @param userRequestDto User data containing information for updating the user.
     * @return The updated User object.
     * @throws CustomException If the user with the provided ID is not found.
     */
    @Override
    public User updateUser(UUID id, UserRequestDto userRequestDto) {
        User existingUser = findById(id);
        return update(existingUser, userRequestDto);
    }

    private User update(User existingUser, UserRequestDto userRequestDto) {
        existingUser.setDOB(userRequestDto.getDOB());
        existingUser.setFirstName(userRequestDto.getFirstName());
        existingUser.setLastName(userRequestDto.getLastName());
        existingUser.setEmail(userRequestDto.getEmail());
        existingUser.setUserName(userRequestDto.getUserName());
        return userRepository.save(existingUser);
    }

    private User findById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new CustomException(localService.getMessage("user.not.found"), HttpStatus.NOT_FOUND));
    }


    /**
     * Finds a user by their username.
     *
     * @param userName The username of the user to find.
     * @return The User object with the matching username, or null if not found.
     * @throws RuntimeException (or a more specific exception type if applicable)
     *                          If there is an unexpected error during the lookup process.
     */
    @Override
    public Optional<User> findByUserName(String userName) {
        String usernameObj = toLowerCase(userName);
        return userRepository.findByUserName(usernameObj);
    }


    /**
     * Finds a list of users by their first name.
     *
     * @param firstName The first name to search for in users.
     * @return A list of User objects matching the provided first name,
     * or an empty list if no matches are found.
     * @throws RuntimeException (or a more specific exception type if applicable)
     *                          If there is an unexpected error during the lookup process.
     */
    @Override
    public List<User> findByFirstName(String firstName) {
        String name = toLowerCase(firstName);
        return userRepository.findByFirstName(name);
    }

    /**
     * Finds a list of users by their first name.
     *
     * @param lastName The first name to search for in users.
     * @return A list of User objects matching the provided last name,
     * or an empty list if no matches are found.
     * @throws RuntimeException (or a more specific exception type if applicable)
     *                          If there is an unexpected error during the lookup process.
     */
    @Override
    public List<User> findByLastName(String lastName) {
        String lastnameObj = toLowerCase(lastName);
        return userRepository.findBylastName(lastnameObj);
    }

    /**
     * Finds a users by their email Address
     *
     * @param email The email to search for in users.
     * @return A User objects matching the provided  email,
     * or an empty user if no matches are found.
     * @throws RuntimeException (or a more specific exception type if applicable)
     *                          If there is an unexpected error during the lookup process.
     */
    @Override
    public Optional<User> findByEmailAddress(String email) {
        String emailObj = toLowerCase(email);
        return userRepository.findByEmail(emailObj);
    }

    @Override
    public Boolean deleteById(UUID id) {
        userRepository.deleteById(id);
        return true;
    }


    private String toLowerCase(String input) {
        if (input != null) {
            return input.toLowerCase();
        }
        return null;
    }
}
