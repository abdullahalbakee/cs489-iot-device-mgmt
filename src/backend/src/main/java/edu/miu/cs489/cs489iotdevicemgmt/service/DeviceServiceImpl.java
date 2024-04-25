package edu.miu.cs489.cs489iotdevicemgmt.service;

import edu.miu.cs489.cs489iotdevicemgmt.dto.DeviceDto;
import edu.miu.cs489.cs489iotdevicemgmt.exception.ResourceNotFoundException;
import edu.miu.cs489.cs489iotdevicemgmt.mapper.AddressMapper;
import edu.miu.cs489.cs489iotdevicemgmt.mapper.DeviceMapper;
import edu.miu.cs489.cs489iotdevicemgmt.mapper.UserMapper;
import edu.miu.cs489.cs489iotdevicemgmt.model.Device;
import edu.miu.cs489.cs489iotdevicemgmt.model.User;
import edu.miu.cs489.cs489iotdevicemgmt.repository.AddressRepository;
import edu.miu.cs489.cs489iotdevicemgmt.repository.ClientRepository;
import edu.miu.cs489.cs489iotdevicemgmt.repository.DeviceRepository;
import edu.miu.cs489.cs489iotdevicemgmt.repository.UserRepository;
import edu.miu.cs489.cs489iotdevicemgmt.service.security.Roles;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DeviceServiceImpl implements DeviceService {
    private final DeviceRepository deviceRepository;
    private final AddressRepository addressRepository;
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public DeviceServiceImpl(DeviceRepository deviceRepository, AddressRepository addressRepository, ClientRepository clientRepository, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.deviceRepository = deviceRepository;
        this.addressRepository = addressRepository;
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public List<DeviceDto> getAll(User client) {
        return deviceRepository
                .findAllByClientId(client.id)
                .stream()
                .map(DeviceMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public DeviceDto create(DeviceDto deviceDto, User loggedInClient) {
        var device = DeviceMapper.toEntity(deviceDto);

        var address = AddressMapper.toEntity(deviceDto.address());
        var savedAddress = addressRepository.save(address);
        device.setAddress(savedAddress);

        var client = clientRepository.findByUserId(loggedInClient.id).orElse(null);
        device.setClient(client);

        var user = formatDeviceAsUser(deviceDto);
        var savedUser = userRepository.save(user);
        device.setUser(savedUser);

         var savedDevice = deviceRepository.save(device);
         return DeviceMapper.toDto(savedDevice);
    }

    private User formatDeviceAsUser(DeviceDto deviceDto) {
        var user = UserMapper.toEntity(deviceDto.user());
        user.id = null;
        user.username = UUID.randomUUID().toString();
        user.password = passwordEncoder.encode(user.password);
        user.role = Roles.DEVICE;
        return user;
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
