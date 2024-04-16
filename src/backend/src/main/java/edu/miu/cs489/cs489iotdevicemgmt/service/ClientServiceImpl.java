package edu.miu.cs489.cs489iotdevicemgmt.service;

import edu.miu.cs489.cs489iotdevicemgmt.dto.ClientDto;
import edu.miu.cs489.cs489iotdevicemgmt.dto.ClientRequestDto;
import edu.miu.cs489.cs489iotdevicemgmt.exception.ResourceNotFoundException;
import edu.miu.cs489.cs489iotdevicemgmt.mapper.ClientMapper;
import edu.miu.cs489.cs489iotdevicemgmt.model.Client;
import edu.miu.cs489.cs489iotdevicemgmt.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper mapper = ClientMapper.INSTANCE;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<ClientDto> getAll() {
        var clients = clientRepository.findAll();
        return convertToDto(clients);
    }

    @Override
    public ClientDto create(ClientRequestDto clientRequestDto) {
        var clientModel = mapper.clientRequestDtoToClient(clientRequestDto);
        var savedClient = clientRepository.save(clientModel);
        return mapper.clientToClientDto(savedClient);
    }

    @Override
    public ClientDto update(Long clientId, ClientRequestDto clientRequestDto) {
        var existingClient = getById(clientId);
        if (existingClient == null) {
            throw new ResourceNotFoundException("Client not found with client id: " + clientId);
        }
        var clientModel = mapper.clientRequestDtoToClient(clientRequestDto);
        clientModel.setId(clientId);
        var savedClient = clientRepository.save(clientModel);
        return mapper.clientToClientDto(savedClient);
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
        return clients.stream().map(c-> mapper.clientToClientDto(c)).toList();
    }
}
