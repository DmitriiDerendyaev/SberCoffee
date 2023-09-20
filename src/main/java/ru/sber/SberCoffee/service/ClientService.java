package ru.sber.SberCoffee.service;

import ru.sber.SberCoffee.entity.Client;

public interface ClientService {
    Client getClient(Long id);

    Long save(Client client);
}
