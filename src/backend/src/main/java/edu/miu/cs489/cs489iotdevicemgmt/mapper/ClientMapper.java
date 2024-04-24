package edu.miu.cs489.cs489iotdevicemgmt.mapper;

import edu.miu.cs489.cs489iotdevicemgmt.dto.ClientDto;
import edu.miu.cs489.cs489iotdevicemgmt.model.Client;

public class ClientMapper {
    public static ClientDto toDto(Client client) {
        return ClientDto.builder()
                .id(client.getId())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .address(AddressMapper.toDto(client.getAddress()))
                .user(UserMapper.toDto(client.getUser()))
                .build();
    }

    public static Client toEntity(ClientDto clientDto) {
        return Client.builder()
                .firstName(clientDto.firstName())
                .lastName(clientDto.lastName())
                .build();
    }
}
