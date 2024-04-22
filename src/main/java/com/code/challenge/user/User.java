/**
 *
 */
package com.code.challenge.user;

import com.code.challenge.utils.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

/**
 * @author Ram Thapa
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "User_challenge")
public class User extends BaseEntity implements UserDetails {

    @Id
    @GeneratedValue
    private UUID id;
    @Column(unique = true)
    private String userName;
    private String password;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserType userType;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private Date DOB;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorites = new ArrayList<SimpleGrantedAuthority>();
        authorites.add(new SimpleGrantedAuthority("ROLE_" + userType.toString()));
        return authorites;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
