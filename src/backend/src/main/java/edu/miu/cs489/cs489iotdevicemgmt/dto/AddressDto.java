package edu.miu.cs489.cs489iotdevicemgmt.dto;

import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
public record AddressDto (
    Long id,
    String firstLine,
    String secondLine,
    String city,
    String state,
    String zip,
    String country) {
}