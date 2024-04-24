package edu.miu.cs489.cs489iotdevicemgmt.controller;

import edu.miu.cs489.cs489iotdevicemgmt.dto.LoginDto;
import edu.miu.cs489.cs489iotdevicemgmt.dto.UserDto;
import edu.miu.cs489.cs489iotdevicemgmt.service.UserService;
import edu.miu.cs489.cs489iotdevicemgmt.service.security.JwtService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = {"/auth"})
public class AuthController {

    private JwtService jwtService;
    private AuthenticationManager authenticationManager;
    private UserService userService;

    public AuthController(JwtService jwtService,
                          AuthenticationManager authenticationManager,
                          UserService userService) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @PostMapping(value = {"/login"})
    public ResponseEntity<LoginDto> authenticateUser(@Valid @RequestBody LoginDto loginDto) throws Exception {
        LoginDto userAuthResponse = null;
        try {
            var username = loginDto.username();
            var password = loginDto.password();
            var authentication = new UsernamePasswordAuthenticationToken(username, password);
            authenticationManager.authenticate(authentication);
            var user = userService.getUser(username);
            var jwtToken = jwtService.generateToken(user);
            if(user != null) {
                userAuthResponse =
                        LoginDto.builder()
                                .username(user.username())
                                .token(jwtToken)
                                .role(user.role())
                                .build();
            }
        } catch (Exception ex) {
            System.out.println("UserAuthException is: " + ex);
            throw ex;
        }
        return ResponseEntity.ok(userAuthResponse);
    }

}
