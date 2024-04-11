package edu.miu.cs489.cs489iotdevicemgmt.service;

import edu.miu.cs489.cs489iotdevicemgmt.model.Device;
import edu.miu.cs489.cs489iotdevicemgmt.repository.DeviceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {
    private final DeviceRepository deviceRepository;

    public DeviceServiceImpl(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Override
    public List<Device> getAll() {
        return deviceRepository.findAll();
    }

    @Override
    public Device create(Device device) {
        return deviceRepository.save(device);
    }

    @Override
    public Device update(Long deviceId, Device device) {
        var existingDevice = getById(deviceId);
        if(existingDevice == null) return null;
        existingDevice.setName(device.getName());
        existingDevice.setSerialNumber(device.getSerialNumber());
        return deviceRepository.save(existingDevice);
    }

    @Override
    public boolean delete(Long deviceId) {
        var existingDevice = getById(deviceId);
        if(existingDevice == null) return false;
        deviceRepository.deleteById(deviceId);
        return true;
    }

    private Device getById(Long deviceId) {
        return deviceRepository.findById(deviceId).orElse(null);
    }
}
