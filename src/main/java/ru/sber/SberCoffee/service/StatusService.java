package ru.sber.SberCoffee.service;

import ru.sber.SberCoffee.entity.Staff;
import ru.sber.SberCoffee.entity.Status;

import java.util.List;
import java.util.Optional;

public interface StatusService {
    public List<Status> getAllStatus();
    public Optional<Status> getStatusById(int id);
    public Status createStatus(Status status);
    public Status updateStatus(int id, Status status);
    public boolean deleteStatus(int id);

}
