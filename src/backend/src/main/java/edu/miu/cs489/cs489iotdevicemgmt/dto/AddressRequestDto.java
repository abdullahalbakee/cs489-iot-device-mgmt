package edu.miu.cs489.cs489iotdevicemgmt.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public record AddressRequestDto(
        @NotEmpty(message = "Address must have first line")
        String firstLine,
        String secondLine,
        String city,
        String state,
        String zip,
        String country)  {
}
