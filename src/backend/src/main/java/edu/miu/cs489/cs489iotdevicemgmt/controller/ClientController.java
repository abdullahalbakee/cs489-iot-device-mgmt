package edu.miu.cs489.cs489iotdevicemgmt.controller;

import edu.miu.cs489.cs489iotdevicemgmt.dto.ClientDto;
import edu.miu.cs489.cs489iotdevicemgmt.dto.ClientRequestDto;
import edu.miu.cs489.cs489iotdevicemgmt.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    //@RolesAllowed("ROLE_ADMIN") ??? does it work
    public ResponseEntity<List<ClientDto>> getAll() {
        List<ClientDto> all = clientService.getAll();
        return ResponseEntity.ok(all);
    }

    @PostMapping
    public ResponseEntity<ClientDto> create(@RequestBody @Valid ClientRequestDto clientRequestDto) {
        var createdClient = clientService.create(clientRequestDto);
        return ResponseEntity.ok(createdClient);
    }

    @PutMapping("/{clientId}")
    public ResponseEntity<ClientDto> update(@PathVariable Long clientId, @RequestBody @Valid ClientRequestDto clientRequestDto) {
        var updatedClient = clientService.update(clientId, clientRequestDto);
        return ResponseEntity.ok(updatedClient);
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<?> delete(@PathVariable Long clientId) {
        clientService.delete(clientId);
        return ResponseEntity.noContent().build();
    }
}
