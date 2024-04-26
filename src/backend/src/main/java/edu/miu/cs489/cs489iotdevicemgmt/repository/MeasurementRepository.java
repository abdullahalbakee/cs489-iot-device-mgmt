package edu.miu.cs489.cs489iotdevicemgmt.repository;

import edu.miu.cs489.cs489iotdevicemgmt.model.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeasurementRepository extends JpaRepository<Measurement, Long> {
    List<Measurement> findByDeviceId(Long deviceId);
    void deleteByDeviceId(Long deviceId);
}
