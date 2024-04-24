package edu.miu.cs489.cs489iotdevicemgmt.dto;

import lombok.Builder;

@Builder
public record LoginDto(
        String username,
        String password,
        String token,
        String role
) {
}
