package com.workshop.ivar.service;

import com.workshop.ivar.dao.IUserDao;
import com.workshop.ivar.dto.AuthenticationRequest;
import com.workshop.ivar.service.dispatcher.IAuthenticationService;
import com.workshop.ivar.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

/**
 * @author cuongnd9
 * @date 03/04/2023
 * @project ivar
 * @package com.workshop.ivar.service.dispatcher
 */
@Service
@RequiredArgsConstructor
public class AuthenticationService implements IAuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final IUserDao userDao;

    @Override
    public String authenticate(AuthenticationRequest request) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        ));

        final UserDetails userDetails = userDao.findUserByEmail(request.getEmail());

        if (Objects.isNull(userDetails)) return "Some error has occur when authenticate your account";

        Map<String, Object> claims = Map.of(
                "position", "junior"
        );

        return jwtUtil.generateToken(userDetails, claims);
    }
}
