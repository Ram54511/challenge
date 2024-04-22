/**
 *
 */
package com.code.challenge.user.service;

import com.code.challenge.user.User;
import com.code.challenge.user.UserRequestDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Ram Thapa
 */

public interface UserService {
    User createUser(UserRequestDto userRequestDto);

    User updateUser(UUID id, UserRequestDto userRequestDto);

    Optional<User> findByUserName(String userName);

    List<User> findByFirstName(String firstName);

    List<User> findByLastName(String lastName);

    Optional<User>findByEmailAddress(String email);

    Boolean deleteById(UUID id);

}
