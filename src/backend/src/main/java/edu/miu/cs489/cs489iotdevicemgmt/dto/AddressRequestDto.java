package edu.miu.cs489.cs489iotdevicemgmt.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequestDto {
    @NotEmpty(message = "Address must have first line")
    private String firstLine;
    private String secondLine;
    private String city;
    private String state;
    private String zip;
    private String country;
}
