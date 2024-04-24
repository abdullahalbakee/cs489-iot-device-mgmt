package edu.miu.cs489.cs489iotdevicemgmt.service;

import edu.miu.cs489.cs489iotdevicemgmt.dto.MeasurementDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MeasurementService {
    List<MeasurementDto> getAll();
    MeasurementDto create(MeasurementDto device);
}
