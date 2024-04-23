package edu.miu.cs489.cs489iotdevicemgmt.mapper;


import edu.miu.cs489.cs489iotdevicemgmt.dto.AddressDto;
import edu.miu.cs489.cs489iotdevicemgmt.dto.AddressRequestDto;
import edu.miu.cs489.cs489iotdevicemgmt.model.Address;


public class AddressMapper {


    public static AddressDto addressToAddressDto(Address address) {
        return AddressDto.builder()
                .build();
    }

    public static Address addressRequestDtoToAddress(AddressRequestDto addressRequestDto) {
        return Address.builder().build();
    }
}
