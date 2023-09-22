package ru.sber.SberCoffee.service.impl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sber.SberCoffee.entity.Client;
import ru.sber.SberCoffee.entity.Staff;
import ru.sber.SberCoffee.repository.ClientRepo;
import ru.sber.SberCoffee.service.ClientService;

import java.util.List;
import java.util.Optional;

/**
 * The type Client service.
 */
@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepo clientRepo;

    @Override
    public List<Client> getAllClient() {
        return clientRepo.findAll();
    }

    @Override
    public Optional<Client> getClientById(Long id) {
        return clientRepo.findById(id);
    }

    @Override
    public Client createClient(Client client) {
        return clientRepo.save(client);
    }

    @Override
    public Client updateClient(Long id, Client newClient) {
        if(clientRepo.existsById(id)){
            newClient.setId(id);
            return clientRepo.save(newClient);
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteClient(Long id) {
        if(clientRepo.existsById(id)){
            clientRepo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
