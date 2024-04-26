package edu.miu.cs489.cs489iotdevicemgmt.service;

import edu.miu.cs489.cs489iotdevicemgmt.dto.MeasurementDto;
import edu.miu.cs489.cs489iotdevicemgmt.exception.ResourceNotFoundException;
import edu.miu.cs489.cs489iotdevicemgmt.mapper.MeasurementMapper;
import edu.miu.cs489.cs489iotdevicemgmt.model.Client;
import edu.miu.cs489.cs489iotdevicemgmt.model.Device;
import edu.miu.cs489.cs489iotdevicemgmt.model.Measurement;
import edu.miu.cs489.cs489iotdevicemgmt.model.User;
import edu.miu.cs489.cs489iotdevicemgmt.repository.ClientRepository;
import edu.miu.cs489.cs489iotdevicemgmt.repository.DeviceRepository;
import edu.miu.cs489.cs489iotdevicemgmt.repository.MeasurementRepository;
import edu.miu.cs489.cs489iotdevicemgmt.repository.UserRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MeasurementServiceImpl implements MeasurementService {
    private final MeasurementRepository measurementRepository;
    private final DeviceRepository deviceRepository;
    private final UserRepository userRepository;
    private final ClientRepository clientRepository;

    public MeasurementServiceImpl(MeasurementRepository measurementRepository, DeviceRepository deviceRepository, UserRepository userRepository, ClientRepository clientRepository) {
        this.measurementRepository = measurementRepository;
        this.deviceRepository = deviceRepository;
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public List<MeasurementDto> getAll(User loggedInClient, String deviceUsername) {
        var deviceAsUser = userRepository.findUserByUsername(deviceUsername).orElse(null);
        if(deviceAsUser == null) throw new ResourceNotFoundException("Device not found with device id: " + deviceUsername);
        var device = deviceRepository.findOneByUserId(deviceAsUser.id).orElse(null);
        if(device == null) throw new ResourceNotFoundException("Device not found with id " + deviceUsername);
        var client = clientRepository.findByUserId(loggedInClient.id).orElse(null);
        if(client == null || device.getClient().getId() != client.getId())
            throw new AccessDeniedException("Client does not have access for device: " + deviceUsername);
        var measurements = measurementRepository.findByDeviceId(device.getId());
        return measurements.stream().map(MeasurementMapper::toDto).toList();
    }

    @Override
    public MeasurementDto create(MeasurementDto measurementDto, User loggedInDevice) {
        var device = getActiveDevice(loggedInDevice);
        var measurement = MeasurementMapper.toEntity(measurementDto);
        if(measurement.dateTime == null) {
            measurement.dateTime = LocalDateTime.now();
        }
        measurement.setDevice(device);
        var savedMeasurement = measurementRepository.save(measurement);
        return MeasurementMapper.toDto(savedMeasurement);
    }

    @Override
    public MeasurementDto update(Long measurementId, MeasurementDto measurementDto, User loggedInClient) {
        var existingMeasurement = getMeasurementIfValid(measurementId, loggedInClient);
        var measurement = MeasurementMapper.toEntity(measurementDto);
        if(measurement.dateTime == null) {
            measurement.dateTime = LocalDateTime.now();
        }
        existingMeasurement.setDateTime(measurement.dateTime);
        existingMeasurement.setValue(measurement.value);
        var updatedMeasurement = measurementRepository.save(existingMeasurement);
        return MeasurementMapper.toDto(updatedMeasurement);
    }

    @Override
    public void delete(Long measurementId, User loggedInClient) {
        getMeasurementIfValid(measurementId, loggedInClient);
        measurementRepository.deleteById(measurementId);
    }

    private Measurement getMeasurementIfValid(Long measurementId, User loggedInClient) {
        var client = getActiveClient(loggedInClient);
        var existingMeasurement = measurementRepository.findById(measurementId).orElse(null);
        if(existingMeasurement == null) throw new ResourceNotFoundException("Measurement not found with id " + measurementId);
        if(existingMeasurement.device.getClient().getId() != client.getId()) {
            throw new AccessDeniedException("Client does not have access for measurement: " + measurementId);
        }
        return existingMeasurement;
    }

    private Device getActiveDevice(User loggedInDevice) {
        var device = deviceRepository.findOneByUserId(loggedInDevice.id).orElse(null);
        if(device == null) {
            throw new AccessDeniedException("Device not found");
        }
        return device;
    }

    private Client getActiveClient(User loggedInClient) {
        var client = clientRepository.findByUserId(loggedInClient.id).orElse(null);
        if(client == null) {
            throw new AccessDeniedException("Client not found");
        }
        return client;
    }
}
