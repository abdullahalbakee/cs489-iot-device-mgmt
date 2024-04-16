package edu.miu.cs489.cs489iotdevicemgmt.mapper;


import edu.miu.cs489.cs489iotdevicemgmt.dto.AddressDto;
import edu.miu.cs489.cs489iotdevicemgmt.dto.AddressRequestDto;
import edu.miu.cs489.cs489iotdevicemgmt.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressMapper {

    AddressMapper INSTANCE = Mappers.getMapper( AddressMapper.class );

    AddressDto addressToAddressDto(Address address);

    Address addressRequestToAddress(AddressRequestDto addressRequestDto);
}
