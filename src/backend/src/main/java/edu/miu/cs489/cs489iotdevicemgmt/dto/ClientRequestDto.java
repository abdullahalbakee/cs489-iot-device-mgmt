package edu.miu.cs489.cs489iotdevicemgmt.dto;

import lombok.Builder;

@Builder
public record ClientRequestDto (
    String firstName) {
}
