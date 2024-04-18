package edu.miu.cs489.cs489iotdevicemgmt.dto;

import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {
    private Long id;
    private String firstLine;
    private String secondLine;
    private String city;
    private String state;
    private String zip;
    private String country;
}