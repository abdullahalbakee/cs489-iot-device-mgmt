package edu.miu.cs489.cs489iotdevicemgmt.controller;

import edu.miu.cs489.cs489iotdevicemgmt.dto.AddressDto;
import edu.miu.cs489.cs489iotdevicemgmt.dto.AddressRequest;
import edu.miu.cs489.cs489iotdevicemgmt.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public ResponseEntity<List<AddressDto>> getAll() {
        List<AddressDto> all = addressService.getAll();
        return ResponseEntity.ok(all);
    }

    @PostMapping
    public ResponseEntity<AddressDto> create(@RequestBody @Valid AddressRequest addressRequest) {
        var createdAddress = addressService.create(addressRequest);
        return ResponseEntity.ok(createdAddress);
    }

    @PutMapping("/{addressId}")
    public ResponseEntity<AddressDto> update(@PathVariable Long addressId, @RequestBody @Valid AddressRequest addressRequest) {
        var updatedAddress = addressService.update(addressId, addressRequest);
        return ResponseEntity.ok(updatedAddress);
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<?> delete(@PathVariable Long addressId) {
        addressService.delete(addressId);
        return ResponseEntity.noContent().build();
    }
}