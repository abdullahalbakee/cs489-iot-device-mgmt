package edu.miu.cs489.cs489iotdevicemgmt.mapper;

import edu.miu.cs489.cs489iotdevicemgmt.dto.DeviceDto;
import edu.miu.cs489.cs489iotdevicemgmt.dto.MeasurementDto;
import edu.miu.cs489.cs489iotdevicemgmt.model.Device;
import edu.miu.cs489.cs489iotdevicemgmt.model.Measurement;

public class MeasurementMapper {
    public static MeasurementDto toDto(Measurement measurement) {
        return MeasurementDto.builder()
                .dateTime(measurement.dateTime)
                .value(measurement.value)
                .build();
    }

    public static Measurement toEntity(MeasurementDto measurementDto) {
        return Measurement.builder()
                .dateTime(measurementDto.dateTime())
                .value(measurementDto.value())
                .build();
    }
}
