package edu.miu.cs489.cs489iotdevicemgmt.service;

import edu.miu.cs489.cs489iotdevicemgmt.dto.DeviceDto;

import java.util.List;

public interface DeviceService {
    List<DeviceDto> getAll();
    DeviceDto create(DeviceDto device);
    DeviceDto update(Long deviceId, DeviceDto device);
    void delete(Long deviceId);
}
