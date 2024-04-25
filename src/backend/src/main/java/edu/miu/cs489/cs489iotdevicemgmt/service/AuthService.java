package edu.miu.cs489.cs489iotdevicemgmt.service;

import edu.miu.cs489.cs489iotdevicemgmt.dto.LoginDto;

public interface AuthService {
    LoginDto login(LoginDto loginDto);
}
