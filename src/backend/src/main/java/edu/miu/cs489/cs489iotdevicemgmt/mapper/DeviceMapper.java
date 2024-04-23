package edu.miu.cs489.cs489iotdevicemgmt.mapper;

import edu.miu.cs489.cs489iotdevicemgmt.dto.DeviceDto;
import edu.miu.cs489.cs489iotdevicemgmt.model.Device;

public class DeviceMapper {
    public static DeviceDto toDto(Device device) {
        return DeviceDto.builder()
                .name(device.getName())
                .serial(device.getSerialNumber())
                .build();
    }

    public static Device toEntity(DeviceDto deviceDto) {
        return Device.builder()
                .name(deviceDto.name())
                .serialNumber(deviceDto.serial())
                .build();
    }
}