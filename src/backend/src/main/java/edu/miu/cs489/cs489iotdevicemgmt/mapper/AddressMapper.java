package edu.miu.cs489.cs489iotdevicemgmt.mapper;


import edu.miu.cs489.cs489iotdevicemgmt.dto.AddressDto;
import edu.miu.cs489.cs489iotdevicemgmt.model.Address;


public class AddressMapper {
    public static AddressDto addressToAddressDto(Address address) {
        return AddressDto.builder()
                .id(address.getId())
                .firstLine(address.getFirstLine())
                .secondLine(address.getSecondLine())
                .city(address.getCity())
                .state(address.getState())
                .zip(address.getZip())
                .country(address.getCountry())
                .build();
    }

    public static Address addressRequestDtoToAddress(AddressDto addressDto) {
        return Address.builder()
                .firstLine(addressDto.firstLine())
                .secondLine(addressDto.secondLine())
                .city(addressDto.city())
                .state(addressDto.state())
                .zip(addressDto.zip())
                .country(addressDto.country())
                .build();
    }
}
