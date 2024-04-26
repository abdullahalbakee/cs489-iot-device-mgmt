package edu.miu.cs489.cs489iotdevicemgmt;

import edu.miu.cs489.cs489iotdevicemgmt.dto.DeviceDto;
import edu.miu.cs489.cs489iotdevicemgmt.mapper.DeviceMapper;
import edu.miu.cs489.cs489iotdevicemgmt.model.Address;
import edu.miu.cs489.cs489iotdevicemgmt.model.Device;
import edu.miu.cs489.cs489iotdevicemgmt.model.User;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class DeviceMapperTest
{
    @Test
    void testToEntity() {
        var dto = DeviceDto.builder().name("test").serial("123456").build();
        var entity = DeviceMapper.toEntity(dto);
        assertThat(entity.getName(), equalTo("test"));
        assertThat(entity.getSerialNumber(), equalTo("123456"));
    }

    @Test
    void testToDto() {
        var entity = Device.builder()
                .id(1L)
                .name("abcd")
                .serialNumber("223344")
                .user(new User())
                .address(new Address())
                .build();
        var dto = DeviceMapper.toDto(entity);
        assertThat(dto.name(), equalTo("abcd"));
        assertThat(dto.serial(), equalTo("223344"));
    }
}
