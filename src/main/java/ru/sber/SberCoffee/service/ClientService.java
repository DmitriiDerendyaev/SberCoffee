package ru.sber.SberCoffee.service;

import ru.sber.SberCoffee.entity.Client;
import ru.sber.SberCoffee.entity.Staff;

import java.util.List;
import java.util.Optional;

/**
 * The interface Client service.
 */
public interface ClientService {
    /**
     * Gets all client.
     *
     * @return the all client
     */
    List<Client> getAllClient();

    /**
     * Gets client by id.
     *
     * @param id the id
     * @return the client by id
     */
    Optional<Client> getClientById(Long id);

    /**
     * Create client client.
     *
     * @param client the client
     * @return the client
     */
    Client createClient(Client client);

    /**
     * Update client client.
     *
     * @param id        the id
     * @param newClient the new client
     * @return the client
     */
    Client updateClient(Long id, Client newClient);

    /**
     * Delete client boolean.
     *
     * @param id the id
     * @return the boolean
     */
    boolean deleteClient(Long id);
}
