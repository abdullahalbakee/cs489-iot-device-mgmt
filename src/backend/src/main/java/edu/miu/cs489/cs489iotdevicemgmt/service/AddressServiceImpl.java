package edu.miu.cs489.cs489iotdevicemgmt.service;

import edu.miu.cs489.cs489iotdevicemgmt.dto.AddressDto;
import edu.miu.cs489.cs489iotdevicemgmt.dto.AddressRequest;
import edu.miu.cs489.cs489iotdevicemgmt.exception.ResourceNotFoundException;
import edu.miu.cs489.cs489iotdevicemgmt.mapper.AddressMapper;
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
    public List<AddressDto> getAll() {
        var addresses = addressRepository.findAll();
        return convertToDto(addresses);
    }

    @Override
    public AddressDto create(AddressRequest addressRequest) {
        var address = AddressMapper.INSTANCE.addressRequestToAddress(addressRequest);
        var savedAddress = addressRepository.save(address);
        return AddressMapper.INSTANCE.addressToAddressDto(savedAddress);
    }

    @Override
    public AddressDto update(Long addressId, AddressRequest address) {
        var existingAddress = getAddressById(addressId);
        if(existingAddress == null) {
            throw new ResourceNotFoundException("Address not found with address id: " + addressId);
        }
        var addressModel = AddressMapper.INSTANCE.addressRequestToAddress(address);
        addressModel.setId(addressId);
        var updatedAddress = addressRepository.save(addressModel);
        return AddressMapper.INSTANCE.addressToAddressDto(updatedAddress);
    }

    @Override
    public void delete(Long addressId) {
        var existingAddress = getAddressById(addressId);
        if(existingAddress == null) {
            throw new ResourceNotFoundException("Address not found with address id: " + addressId);
        }
        addressRepository.deleteById(addressId);
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

    private static List<AddressDto> convertToDto(List<Address> addresses) {
        return addresses.stream().map(AddressMapper.INSTANCE::addressToAddressDto).toList();
    }
}
