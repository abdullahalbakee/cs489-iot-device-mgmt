package edu.miu.cs489.cs489iotdevicemgmt.repository;

import edu.miu.cs489.cs489iotdevicemgmt.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
