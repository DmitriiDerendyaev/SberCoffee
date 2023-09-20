package ru.sber.SberCoffee.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sber.SberCoffee.model.Client;
import ru.sber.SberCoffee.repository.ClientRepo;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class ClientServiceImpl implements ClientService{
    ClientRepo clientRepo;
    @Override
    public Client getClient(Long id) {
        return null;
    }

    @Override
    public Long save(Client client) {
        clientRepo.save()
        return null;
    }
}
