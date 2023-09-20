package ru.sber.SberCoffee.service.impl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sber.SberCoffee.entity.Client;
import ru.sber.SberCoffee.repository.ClientRepo;
import ru.sber.SberCoffee.service.ClientService;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class ClientServiceImpl implements ClientService {
    ClientRepo clientRepo;
    @Override
    public Client getClient(Long id) {
        return null;
    }

    @Override
    public Long save(Client client) {
        clientRepo.save(client);
        return null;
    }
}
