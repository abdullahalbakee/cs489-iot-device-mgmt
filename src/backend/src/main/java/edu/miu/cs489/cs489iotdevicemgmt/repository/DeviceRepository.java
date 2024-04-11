package edu.miu.cs489.cs489iotdevicemgmt.repository;

import edu.miu.cs489.cs489iotdevicemgmt.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, Long> {
}
