package edu.miu.cs489.cs489iotdevicemgmt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
public record ClientDto (
    String firstName) {
}
