package edu.miu.cs489.cs489iotdevicemgmt.service;

import edu.miu.cs489.cs489iotdevicemgmt.dto.AddressDto;
import edu.miu.cs489.cs489iotdevicemgmt.dto.AddressRequest;
import edu.miu.cs489.cs489iotdevicemgmt.model.Address;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressService {
    List<AddressDto> getAll();
    AddressDto create(AddressRequest address);
    AddressDto update(Long addressId, AddressRequest address);
    void delete(Long addressId);

    List<Address> getAllByCity(String city);
    List<Address> getAllByCityAndState(String city, String state);
    List<Address> findByCountry(String country);
}
