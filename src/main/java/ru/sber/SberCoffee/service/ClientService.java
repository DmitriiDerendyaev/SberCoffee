package ru.sber.SberCoffee.service;

import ru.sber.SberCoffee.model.Client;

public interface ClientService {
    Client getClient(Long id);

    Long save(Client client);
}
