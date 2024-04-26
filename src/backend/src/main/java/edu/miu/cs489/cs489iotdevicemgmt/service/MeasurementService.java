package edu.miu.cs489.cs489iotdevicemgmt.service;

import edu.miu.cs489.cs489iotdevicemgmt.dto.MeasurementDto;
import edu.miu.cs489.cs489iotdevicemgmt.model.User;

import java.util.List;

public interface MeasurementService {
    List<MeasurementDto> getAll(User loggedInClient, String deviceUsername);
    MeasurementDto create(MeasurementDto device, User loggedInDevice);

    MeasurementDto update(Long measurementId, MeasurementDto measurementDto, User loggedInClient);

    void delete(Long measurementId, User loggedInClient);
}
