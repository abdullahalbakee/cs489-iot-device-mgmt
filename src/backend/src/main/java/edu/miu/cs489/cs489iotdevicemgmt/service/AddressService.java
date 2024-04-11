package edu.miu.cs489.cs489iotdevicemgmt.service;

import edu.miu.cs489.cs489iotdevicemgmt.model.Address;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressService {
    List<Address> getAll();
    Address create(Address address);
    Address update(Address address);
    boolean delete(Long addressId);

    List<Address> getAllByCity(String city);
    List<Address> getAllByCityAndState(String city, String state);
    List<Address> findByCountry(String country);
}
