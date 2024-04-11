package edu.miu.cs489.cs489iotdevicemgmt.service;

import edu.miu.cs489.cs489iotdevicemgmt.model.Address;
import edu.miu.cs489.cs489iotdevicemgmt.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Address> getAll() {
        return addressRepository.findAll();
    }

    @Override
    public Address create(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Address update(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public boolean delete(Long addressId) {
        var existingAddress = getAddressById(addressId);
        if(existingAddress == null) return false;
        addressRepository.deleteById(addressId);
        return true;
    }

    @Override
    public List<Address> getAllByCity(String city) {
        return addressRepository.getAllByCity(city);
    }

    @Override
    public List<Address> getAllByCityAndState(String city, String state) {
        return addressRepository.getAllByCityAndState(city, state);
    }

    @Override
    public List<Address> findByCountry(String country) {
        return addressRepository.findByCountry(country);
    }

    private Address getAddressById(Long addressId) {
        return addressRepository.findById(addressId).orElse(null);
    }


}
