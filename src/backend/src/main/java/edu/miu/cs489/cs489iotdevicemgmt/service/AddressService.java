package edu.miu.cs489.cs489iotdevicemgmt.service;

import edu.miu.cs489.cs489iotdevicemgmt.dto.AddressDto;
import edu.miu.cs489.cs489iotdevicemgmt.dto.AddressRequestDto;
import edu.miu.cs489.cs489iotdevicemgmt.model.Address;

import java.util.List;

public interface AddressService {
    List<AddressDto> getAll();
    AddressDto create(AddressRequestDto address);
    AddressDto update(Long addressId, AddressRequestDto address);
    void delete(Long addressId);

    List<Address> getAllByCity(String city);
    List<Address> getAllByCityAndState(String city, String state);
    List<Address> findByCountry(String country);
}
