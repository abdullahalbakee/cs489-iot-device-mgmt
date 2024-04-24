package edu.miu.cs489.cs489iotdevicemgmt.service;

import edu.miu.cs489.cs489iotdevicemgmt.dto.MeasurementDto;

import java.util.List;

public interface MeasurementService {
    List<MeasurementDto> getAll();
    MeasurementDto create(MeasurementDto device);
}
