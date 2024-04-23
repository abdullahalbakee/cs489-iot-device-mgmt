package edu.miu.cs489.cs489iotdevicemgmt.service;

import edu.miu.cs489.cs489iotdevicemgmt.dto.DeviceDto;
import edu.miu.cs489.cs489iotdevicemgmt.exception.ResourceNotFoundException;
import edu.miu.cs489.cs489iotdevicemgmt.mapper.DeviceMapper;
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
    public List<DeviceDto> getAll() {
        return deviceRepository
                .findAll().stream()
                .map(DeviceMapper::toDto)
                .toList();
    }

    @Override
    public DeviceDto create(DeviceDto deviceDto) {
        var device = DeviceMapper.toEntity(deviceDto);
         deviceRepository.save(device);
         return DeviceMapper.toDto(device);
    }

    @Override
    public DeviceDto update(Long deviceId, DeviceDto deviceDto) {
        var existingDevice = getById(deviceId);
        if(existingDevice == null) {
            throw new ResourceNotFoundException("Device not found with id " + deviceId);
        }
        existingDevice.setName(deviceDto.name());
        existingDevice.setSerialNumber(deviceDto.serial());
        var updatedDevice = deviceRepository.save(existingDevice);
        return DeviceMapper.toDto(updatedDevice);
    }

    @Override
    public void delete(Long deviceId) {
        var existingDevice = getById(deviceId);
        if(existingDevice == null) {
            throw new ResourceNotFoundException("Device not found with id " + deviceId);
        }
        deviceRepository.deleteById(deviceId);
    }

    private Device getById(Long deviceId) {
        return deviceRepository.findById(deviceId).orElse(null);
    }
}
