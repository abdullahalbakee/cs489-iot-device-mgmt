package edu.miu.cs489.cs489iotdevicemgmt.service;

import edu.miu.cs489.cs489iotdevicemgmt.dto.DeviceDto;
import edu.miu.cs489.cs489iotdevicemgmt.model.User;

import java.util.List;

public interface DeviceService {
    List<DeviceDto> getAll(User user);
    DeviceDto create(DeviceDto device, User loggedInUser);
    DeviceDto update(Long deviceId, DeviceDto device);
    void delete(Long deviceId);
}
