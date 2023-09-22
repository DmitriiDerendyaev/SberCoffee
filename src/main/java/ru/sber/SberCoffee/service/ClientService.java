package ru.sber.SberCoffee.service;

import ru.sber.SberCoffee.entity.Client;
import ru.sber.SberCoffee.entity.Staff;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    List<Client> getAllClient();
    Optional<Client> getClientById(Long id);
    Client createClient(Client client);
    Client updateClient(Long id, Client newClient);
    boolean deleteClient(Long id);
}
