package edu.miu.cs489.cs489iotdevicemgmt.service;

import edu.miu.cs489.cs489iotdevicemgmt.model.Device;

import java.util.List;

public interface DeviceService {
    List<Device> getAll();
    Device create(Device device);
    Device update(Long deviceId, Device device);
    boolean delete(Long deviceId);
}
