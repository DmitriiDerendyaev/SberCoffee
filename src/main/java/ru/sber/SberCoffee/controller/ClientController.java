package ru.sber.SberCoffee.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sber.SberCoffee.entity.Client;
import ru.sber.SberCoffee.service.ClientService;

import java.util.List;
import java.util.Optional;

/**
 * The type Client controller.
 */
@Slf4j
@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    /**
     * Get all client response entity.
     *
     * @return the response entity
     */
    @GetMapping
    public ResponseEntity<List<Client>> getAllClient(){
        List<Client> clientList = clientService.getAllClient();
        if(clientList.isEmpty()){
            log.warn("No clients found");
            return ResponseEntity.notFound().build();
        }

        log.info("Returned {} clients", clientList.size());
        return ResponseEntity.ok(clientList);
    }

    /**
     * Get client by id response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id){
        Optional<Client> clientOptional = clientService.getClientById(id);

        if(clientOptional.isPresent()){
            log.info("Client with ID {} found", id);
            return ResponseEntity.ok(clientOptional.get());
        } else {
            log.warn("Client with ID {} not found", id);
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Create client response entity.
     *
     * @param client the client
     * @return the response entity
     */
    @PostMapping
    public ResponseEntity<Client> createClient(@Valid @RequestBody Client client){
        if(client == null){
            log.warn("Received a null client request");
            return ResponseEntity.badRequest().build();
        }

        Client createdClient = clientService.createClient(client);
        log.info("Created client with ID {}", createdClient.getId());
        return ResponseEntity.ok(createdClient);
    }

    /**
     * Update client response entity.
     *
     * @param id     the id
     * @param client the client
     * @return the response entity
     */
    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @Valid @RequestBody Client client){
        if (client == null) {
            log.warn("Received a null client update request");
            return ResponseEntity.badRequest().build();
        }

        Client updatedClient = clientService.updateClient(id, client);
        if (updatedClient != null) {
            log.info("Updated client with ID {}", id);
            return ResponseEntity.ok(updatedClient);
        } else {
            log.warn("Client with ID {} not found", id);
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Delete client response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Client> deleteClient(@PathVariable Long id){
        if (clientService.deleteClient(id)) {
            log.info("Deleted client with ID {}", id);
            return ResponseEntity.noContent().build();
        } else {
            log.warn("Client with ID {} not found", id);
            return ResponseEntity.notFound().build();
        }
    }
}
