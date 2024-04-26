package edu.miu.cs489.cs489iotdevicemgmt.controller;

import edu.miu.cs489.cs489iotdevicemgmt.dto.DeviceDto;
import edu.miu.cs489.cs489iotdevicemgmt.service.DeviceService;
import edu.miu.cs489.cs489iotdevicemgmt.service.security.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/devices")
public class DeviceController {
    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping
    public ResponseEntity<List<DeviceDto>> getAllDevices() {
        var client = JwtService.getLoggedInUser();
        var devices = deviceService.getAll(client);
        return ResponseEntity.ok(devices);
    }

    @PostMapping
    public ResponseEntity<DeviceDto> createDevice(@RequestBody DeviceDto device) {
        var client = JwtService.getLoggedInUser();
        var createdDevice = deviceService.create(device, client);
        return ResponseEntity.ok(createdDevice);
    }

    @PutMapping("/{deviceId}")
    public ResponseEntity<DeviceDto> updateDevice(@PathVariable Long deviceId, @RequestBody DeviceDto device) {
        var client = JwtService.getLoggedInUser();
        var updatedDevice = deviceService.update(deviceId, device, client);
        return ResponseEntity.ok(updatedDevice);
    }

    @DeleteMapping("/{deviceId}")
    public ResponseEntity updateDevice(@PathVariable Long deviceId) {
        var client = JwtService.getLoggedInUser();
        deviceService.delete(deviceId, client);
        return ResponseEntity.noContent().build();
    }
}
