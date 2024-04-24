package edu.miu.cs489.cs489iotdevicemgmt.controller;

import edu.miu.cs489.cs489iotdevicemgmt.dto.DeviceDto;
import edu.miu.cs489.cs489iotdevicemgmt.dto.MeasurementDto;
import edu.miu.cs489.cs489iotdevicemgmt.service.DeviceService;
import edu.miu.cs489.cs489iotdevicemgmt.service.MeasurementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {
    private final MeasurementService measurementService;

    public MeasurementController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    @PostMapping
    public ResponseEntity<MeasurementDto> createDevice(@RequestBody MeasurementDto measurementDto) {
        var createdDevice = measurementService.create(measurementDto);
        return ResponseEntity.ok(createdDevice);
    }
}
