package edu.miu.cs489.cs489iotdevicemgmt.service;

import edu.miu.cs489.cs489iotdevicemgmt.dto.LoginDto;
import edu.miu.cs489.cs489iotdevicemgmt.service.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public AuthServiceImpl(JwtService jwtService, AuthenticationManager authenticationManager, UserService userService) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @Override
    public LoginDto login(LoginDto loginDto) {
        var username = loginDto.username();
        var password = loginDto.password();
        var authentication = new UsernamePasswordAuthenticationToken(username, password);
        authenticationManager.authenticate(authentication);
        var user = userService.getUser(username);
        var jwtToken = jwtService.generateToken(user);
        if (user == null) {
            throw new BadCredentialsException("Invalid username or password");
        }
        return LoginDto.builder()
                .username(user.username())
                .token(jwtToken)
                .role(user.role())
                .build();
    }
}
