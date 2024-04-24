package edu.miu.cs489.cs489iotdevicemgmt.service;

import edu.miu.cs489.cs489iotdevicemgmt.dto.ClientDto;

import java.util.List;

public interface ClientService {
    List<ClientDto> getAll();
    ClientDto create(ClientDto clientDto);
    ClientDto update(Long clientId, ClientDto clientDto);
    void delete(Long clientId);
}
