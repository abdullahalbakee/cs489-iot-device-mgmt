package edu.miu.cs489.cs489iotdevicemgmt.repository;

import edu.miu.cs489.cs489iotdevicemgmt.model.Address;
import org.hibernate.annotations.processing.SQL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query("select a from Address a where a.city=:city")
    List<Address> getAllByCity(String city);

    @Query(value = "select * from address where city=:city and state=:state", nativeQuery = true)
    List<Address> getAllByCityAndState(String city, String state);

    List<Address> findByCountry(String country);
}
