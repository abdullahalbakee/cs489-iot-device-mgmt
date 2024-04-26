package edu.miu.cs489.cs489iotdevicemgmt.service;

import edu.miu.cs489.cs489iotdevicemgmt.dto.ClientDto;
import edu.miu.cs489.cs489iotdevicemgmt.exception.ResourceNotFoundException;
import edu.miu.cs489.cs489iotdevicemgmt.mapper.AddressMapper;
import edu.miu.cs489.cs489iotdevicemgmt.mapper.ClientMapper;
import edu.miu.cs489.cs489iotdevicemgmt.mapper.UserMapper;
import edu.miu.cs489.cs489iotdevicemgmt.model.Client;
import edu.miu.cs489.cs489iotdevicemgmt.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final DeviceRepository deviceRepository;
    private final MeasurementRepository measurementRepository;

    public ClientServiceImpl(ClientRepository clientRepository, AddressRepository addressRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, DeviceRepository deviceRepository, MeasurementRepository measurementRepository) {
        this.clientRepository = clientRepository;
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.deviceRepository = deviceRepository;
        this.measurementRepository = measurementRepository;
    }

    @Override
    public List<ClientDto> getAll() {
        var clients = clientRepository.findAll();
        return convertToDto(clients);
    }

    @Transactional
    @Override
    public ClientDto create(ClientDto clientDto) {
        var addressModel = AddressMapper.toEntity(clientDto.address());
        var savedAddress = addressRepository.save(addressModel);

        var userModel = UserMapper.toEntity(clientDto.user());
        userModel.password = passwordEncoder.encode(userModel.password);
        var savedUser = userRepository.save(userModel);

        var clientModel = ClientMapper.toEntity(clientDto);
        clientModel.setAddress(savedAddress);
        clientModel.setUser(savedUser);
        var savedClient = clientRepository.save(clientModel);
        return ClientMapper.toDto(savedClient);
    }

    @Override
    @Transactional
    public ClientDto update(Long clientId, ClientDto clientDto) {
        var existingClient = getById(clientId);
        if (existingClient == null) {
            throw new ResourceNotFoundException("Client not found with client id: " + clientId);
        }

        var existingAddress = existingClient.getAddress();
        existingAddress.setFirstLine(clientDto.address().firstLine());
        existingAddress.setSecondLine(clientDto.address().secondLine());
        existingAddress.setCity(clientDto.address().city());
        existingAddress.setState(clientDto.address().state());
        existingAddress.setZip(clientDto.address().zip());
        existingAddress.setCountry(clientDto.address().country());
        var updatedAddress = addressRepository.save(existingAddress);

        var existingUser = existingClient.getUser();
        existingUser.username = clientDto.user().username();
        existingUser.password = passwordEncoder.encode(clientDto.user().password());
        var updatedUser = userRepository.save(existingUser);

        existingClient.setFirstName(clientDto.firstName());
        existingClient.setLastName(clientDto.lastName());
        existingClient.setAddress(updatedAddress);
        existingClient.setUser(updatedUser);
        var updatedClient = clientRepository.save(existingClient);
        return ClientMapper.toDto(updatedClient);
    }

    @Override
    @Transactional
    public void delete(Long clientId) {
        var existingClient = getById(clientId);
        if(existingClient == null) {
            throw new ResourceNotFoundException("Client not found with client id: " + clientId);
        }
        var existingAddress = existingClient.getAddress();
        addressRepository.delete(existingAddress);

        var existingUser = existingClient.getUser();
        userRepository.delete(existingUser);

        var existingDevices = deviceRepository.findAllByClientId(existingClient.getId());
        for (var device : existingDevices) {
            measurementRepository.deleteByDeviceId(device.getId());
        }
        deviceRepository.deleteByClientId(existingClient.getId());

        clientRepository.delete(existingClient);
    }

    private Client getById(Long clientId) {
        return clientRepository.findById(clientId).orElse(null);
    }

    private List<ClientDto> convertToDto(List<Client> clients) {
        return clients.stream().map(ClientMapper::toDto).toList();
    }
}
