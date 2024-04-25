package edu.miu.cs489.cs489iotdevicemgmt.controller;

import edu.miu.cs489.cs489iotdevicemgmt.dto.MeasurementDto;
import edu.miu.cs489.cs489iotdevicemgmt.service.MeasurementService;
import edu.miu.cs489.cs489iotdevicemgmt.service.security.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {
    private final MeasurementService measurementService;

    public MeasurementController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    @GetMapping
    public ResponseEntity<List<MeasurementDto>> getAllMeasurements(@RequestParam String deviceUsername) {
        var loggedInClient = JwtService.getLoggedInUser();
        var measurements = measurementService.getAll(loggedInClient, deviceUsername);
        return ResponseEntity.ok(measurements);
    }

    @PostMapping
    public ResponseEntity<MeasurementDto> addMeasurement(@RequestBody MeasurementDto measurementDto) {
        var loggedInDevice = JwtService.getLoggedInUser();
        var createdDevice = measurementService.create(measurementDto, loggedInDevice);
        return ResponseEntity.ok(createdDevice);
    }
}
