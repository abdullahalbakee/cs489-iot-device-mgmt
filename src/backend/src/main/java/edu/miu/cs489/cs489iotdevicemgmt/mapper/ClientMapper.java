package edu.miu.cs489.cs489iotdevicemgmt.mapper;


import edu.miu.cs489.cs489iotdevicemgmt.dto.ClientDto;
import edu.miu.cs489.cs489iotdevicemgmt.dto.ClientRequestDto;
import edu.miu.cs489.cs489iotdevicemgmt.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper( ClientMapper.class );

    ClientDto clientToClientDto(Client client);

    Client clientRequestDtoToClient(ClientRequestDto clientRequestDto);
}
