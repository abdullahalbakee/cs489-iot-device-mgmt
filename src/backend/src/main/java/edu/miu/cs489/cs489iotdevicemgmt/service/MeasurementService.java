package edu.miu.cs489.cs489iotdevicemgmt.service;

import edu.miu.cs489.cs489iotdevicemgmt.dto.MeasurementDto;
import edu.miu.cs489.cs489iotdevicemgmt.model.User;

import java.util.List;

public interface MeasurementService {
    List<MeasurementDto> getAll(User user);
    MeasurementDto create(MeasurementDto device, User user);
}
