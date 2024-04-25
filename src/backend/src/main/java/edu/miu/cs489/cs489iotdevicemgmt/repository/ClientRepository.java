package edu.miu.cs489.cs489iotdevicemgmt.repository;

import edu.miu.cs489.cs489iotdevicemgmt.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByUserId(Long userId);
}
