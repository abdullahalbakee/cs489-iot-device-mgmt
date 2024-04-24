package edu.miu.cs489.cs489iotdevicemgmt.service;

import edu.miu.cs489.cs489iotdevicemgmt.dto.UserDto;

public interface UserService {
    UserDto getUser(String username);
}
