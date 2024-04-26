package edu.miu.cs489.cs489iotdevicemgmt.service;

import edu.miu.cs489.cs489iotdevicemgmt.dto.DeviceDto;
import edu.miu.cs489.cs489iotdevicemgmt.model.User;

import java.util.List;

public interface DeviceService {
    List<DeviceDto> getAll(User client);
    DeviceDto create(DeviceDto device, User client);
    DeviceDto update(Long deviceId, DeviceDto device, User client);
    void delete(Long deviceId, User client);
}
