package edu.miu.cs489.cs489iotdevicemgmt.controller;

import edu.miu.cs489.cs489iotdevicemgmt.dto.LoginDto;
import edu.miu.cs489.cs489iotdevicemgmt.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = {"/login"})
    public ResponseEntity<LoginDto> authenticateUser(@Valid @RequestBody LoginDto loginDto) {
        var loginResponse = authService.login(loginDto);
        return ResponseEntity.ok(loginResponse);
    }
}
