package edu.miu.cs489.cs489iotdevicemgmt.service;

import edu.miu.cs489.cs489iotdevicemgmt.dto.ClientDto;
import edu.miu.cs489.cs489iotdevicemgmt.dto.ClientRequestDto;
import edu.miu.cs489.cs489iotdevicemgmt.model.Client;

import java.util.List;

public interface ClientService {
    List<ClientDto> getAll();
    ClientDto create(ClientRequestDto clientRequestDto);
    ClientDto update(Long clientId, ClientRequestDto clientRequestDto);
    void delete(Long clientId);
}
