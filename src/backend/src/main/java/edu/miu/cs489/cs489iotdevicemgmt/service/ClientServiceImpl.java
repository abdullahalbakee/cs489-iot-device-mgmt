package edu.miu.cs489.cs489iotdevicemgmt.service;

import edu.miu.cs489.cs489iotdevicemgmt.dto.ClientDto;
import edu.miu.cs489.cs489iotdevicemgmt.exception.ResourceNotFoundException;
import edu.miu.cs489.cs489iotdevicemgmt.mapper.AddressMapper;
import edu.miu.cs489.cs489iotdevicemgmt.mapper.ClientMapper;
import edu.miu.cs489.cs489iotdevicemgmt.mapper.UserMapper;
import edu.miu.cs489.cs489iotdevicemgmt.model.Client;
import edu.miu.cs489.cs489iotdevicemgmt.repository.AddressRepository;
import edu.miu.cs489.cs489iotdevicemgmt.repository.ClientRepository;
import edu.miu.cs489.cs489iotdevicemgmt.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    public ClientServiceImpl(ClientRepository clientRepository, AddressRepository addressRepository, UserRepository userRepository) {
        this.clientRepository = clientRepository;
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<ClientDto> getAll() {
        var clients = clientRepository.findAll();
        return convertToDto(clients);
    }

    @Override
    public ClientDto create(ClientDto clientDto) {
        var addressModel = AddressMapper.toEntity(clientDto.address());
        var savedAddress = addressRepository.save(addressModel);

        var userModel = UserMapper.toEntity(clientDto.user());
        var savedUser = userRepository.save(userModel);

        var clientModel = ClientMapper.toEntity(clientDto);
        clientModel.setAddress(savedAddress);
        clientModel.setUser(savedUser);
        var savedClient = clientRepository.save(clientModel);
        return ClientMapper.toDto(savedClient);
    }

    @Override
    public ClientDto update(Long clientId, ClientDto clientDto) {
        var existingClient = getById(clientId);
        if (existingClient == null) {
            throw new ResourceNotFoundException("Client not found with client id: " + clientId);
        }
//        var clientModel = mapper.clientRequestDtoToClient(clientRequestDto);
//        clientModel.setId(clientId);
//        var savedClient = clientRepository.save(clientModel);
//        return mapper.clientToClientDto(savedClient);
        return null;
    }

    @Override
    public void delete(Long clientId) {
        var existingClient = getById(clientId);
        if(existingClient == null) {
            throw new ResourceNotFoundException("Client not found with client id: " + clientId);
        }
        clientRepository.deleteById(clientId);
    }

    private Client getById(Long clientId) {
        return clientRepository.findById(clientId).orElse(null);
    }

    private List<ClientDto> convertToDto(List<Client> clients) {
        return clients.stream().map(ClientMapper::toDto).toList();
    }
}
