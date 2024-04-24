package edu.miu.cs489.cs489iotdevicemgmt.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record DeviceDto(
        String name,
        String serial
) {
}

