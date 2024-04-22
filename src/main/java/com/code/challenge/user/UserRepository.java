/**
 *
 */
package com.code.challenge.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Ram Thapa
 */

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByUserName(String userName);

    List<User> findByFirstName(String firstName);

    List<User> findBylastName(String lastName);

    Optional<User> findByEmail(String email);
}
