package ru.sber.SberCoffee.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sber.SberCoffee.entity.Client;
import ru.sber.SberCoffee.service.ClientService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public ResponseEntity<List<Client>> getAllClient(){
        List<Client> clientList = clientService.getAllClient();
        if(clientList.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(clientList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id){
        Optional<Client> clientOptional = clientService.getClientById(id);

        if(clientOptional.isPresent()){
            return ResponseEntity.ok(clientOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client){
        if(client == null){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(clientService.createClient(client));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client client){
        Client updatedClient = clientService.updateClient(id, client);

        if(updatedClient != null){
            return ResponseEntity.ok(updatedClient);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Client> deleteClient(@PathVariable Long id){
        if(clientService.deleteClient(id)){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
