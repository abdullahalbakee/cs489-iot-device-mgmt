package edu.miu.cs489.cs489iotdevicemgmt.service;

import edu.miu.cs489.cs489iotdevicemgmt.dto.MeasurementDto;
import edu.miu.cs489.cs489iotdevicemgmt.mapper.MeasurementMapper;
import edu.miu.cs489.cs489iotdevicemgmt.model.Device;
import edu.miu.cs489.cs489iotdevicemgmt.model.User;
import edu.miu.cs489.cs489iotdevicemgmt.repository.DeviceRepository;
import edu.miu.cs489.cs489iotdevicemgmt.repository.MeasurementRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeasurementServiceImpl implements MeasurementService {
    private final MeasurementRepository measurementRepository;
    private final DeviceRepository deviceRepository;

    public MeasurementServiceImpl(MeasurementRepository measurementRepository, DeviceRepository deviceRepository) {
        this.measurementRepository = measurementRepository;
        this.deviceRepository = deviceRepository;
    }

    @Override
    public List<MeasurementDto> getAll(User user) {
        var device = getActiveDevice(user);
        var measurements = measurementRepository.findByDeviceId(device.getId());
        return measurements.stream().map(MeasurementMapper::toDto).toList();
    }

    @Override
    public MeasurementDto create(MeasurementDto measurementDto, User user) {
        var device = getActiveDevice(user);
        var measurement = MeasurementMapper.toEntity(measurementDto);
        measurement.setDevice(device);
        var savedMeasurement = measurementRepository.save(measurement);
        return MeasurementMapper.toDto(savedMeasurement);
    }

    private Device getActiveDevice(User user) {
        var device = deviceRepository.findOneByUserId(user.id).orElse(null);
        if(device == null) {
            throw new AccessDeniedException("Device not found");
        }
        return device;
    }
}
