package ru.sber.SberCoffee.service;

import ru.sber.SberCoffee.entity.Staff;
import ru.sber.SberCoffee.entity.Status;

import java.util.List;
import java.util.Optional;

/**
 * The interface Status service.
 */
public interface StatusService {
    /**
     * Gets all status.
     *
     * @return the all status
     */
    public List<Status> getAllStatus();

    /**
     * Gets status by id.
     *
     * @param id the id
     * @return the status by id
     */
    public Optional<Status> getStatusById(int id);

    /**
     * Create status status.
     *
     * @param status the status
     * @return the status
     */
    public Status createStatus(Status status);

    /**
     * Update status status.
     *
     * @param id     the id
     * @param status the status
     * @return the status
     */
    public Status updateStatus(int id, Status status);

    /**
     * Delete status boolean.
     *
     * @param id the id
     * @return the boolean
     */
    public boolean deleteStatus(int id);

}
