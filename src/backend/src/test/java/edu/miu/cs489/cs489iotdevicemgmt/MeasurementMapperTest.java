package edu.miu.cs489.cs489iotdevicemgmt;

import edu.miu.cs489.cs489iotdevicemgmt.dto.MeasurementDto;
import edu.miu.cs489.cs489iotdevicemgmt.mapper.MeasurementMapper;
import edu.miu.cs489.cs489iotdevicemgmt.model.Measurement;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class MeasurementMapperTest extends AbstractComponentTest {
    @Test
    public void testToEntity() {
        var measurementDto = new MeasurementDto(null, 100.50);
        var measurement = MeasurementMapper.toEntity(measurementDto);
        assertThat(measurement.dateTime, is(nullValue()));
        assertThat(measurement.value, closeTo(100.50, 0.001));
    }

    @Test
    public void testToDto() {
        var measurement = Measurement.builder()
                .dateTime(LocalDateTime.now())
                .value(10.0)
                .build();
        var measurementDto = MeasurementMapper.toDto(measurement);
        assertThat(measurementDto.dateTime(), is(notNullValue()));
        assertThat(measurementDto.value(), closeTo(10.0, 0.001));
    }
}
