package com.workshop.ivar.dao;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author cuongnd9
 * @date 03/04/2023
 * @project ivar
 * @package com.workshop.ivar.dao
 */
@Repository
@Slf4j
public class UserDao implements IUserDao {

    public UserDetails findUserByEmail(String email) {

        final List<UserDetails> APPLICATION_USERS = List.of(
                new User("cuongnd32@fwd.com.vn",
                        "password",
                        List.of(new SimpleGrantedAuthority("ROLE_ADMIN"))),
                new User("dungnt23@fwd.com.vn",
                        "password",
                        List.of(new SimpleGrantedAuthority("ROLE_USER")))
        );

        log.info("application user: {}", new Gson().toJson(APPLICATION_USERS));

        UserDetails user = APPLICATION_USERS
                .stream()
                .filter(u -> u.getUsername().equals(email))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));

        return user;
    }

}
