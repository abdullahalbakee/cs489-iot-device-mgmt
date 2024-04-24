package edu.miu.cs489.cs489iotdevicemgmt.dto;

import lombok.Builder;

@Builder
public record ClientDto (
        Long id,
        String firstName,
        String lastName,
        String email,
        AddressDto address,
        UserDto user
) {
}
