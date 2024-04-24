package edu.miu.cs489.cs489iotdevicemgmt.mapper;

import edu.miu.cs489.cs489iotdevicemgmt.dto.UserDto;
import edu.miu.cs489.cs489iotdevicemgmt.model.User;

public class UserMapper {
    public static UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.id)
                .username(user.username)
                .build();
    }

    public static User toEntity(UserDto dto) {
        return User.builder()
                .username(dto.username())
                .password(dto.password())
                .build();
    }
}
