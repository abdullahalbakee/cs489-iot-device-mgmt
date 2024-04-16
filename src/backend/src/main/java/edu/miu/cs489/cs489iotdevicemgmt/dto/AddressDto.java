package edu.miu.cs489.cs489iotdevicemgmt.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public record AddressDto (
    Long id,
    String firstLine,
    String secondLine,
    String city,
    String state,
    String zip,
    String country) {

}