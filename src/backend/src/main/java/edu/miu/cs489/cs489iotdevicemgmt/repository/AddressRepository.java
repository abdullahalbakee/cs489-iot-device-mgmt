package edu.miu.cs489.cs489iotdevicemgmt.repository;

import edu.miu.cs489.cs489iotdevicemgmt.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
