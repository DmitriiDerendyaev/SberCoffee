package ru.sber.SberCoffee.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sber.SberCoffee.entity.Status;
import ru.sber.SberCoffee.repository.StaffRepo;
import ru.sber.SberCoffee.repository.StatusRepo;
import ru.sber.SberCoffee.service.StatusService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StatusServiceImpl implements StatusService {

    private final StatusRepo statusRepo;
    @Override
    public List<Status> getAllStatus() {
        return statusRepo.findAll();
    }

    @Override
    public Optional<Status> getStatusById(int id) {
        return statusRepo.findById(id);
    }

    @Override
    public Status createStatus(Status status) {
        return statusRepo.save(status);
    }

    @Override
    public Status updateStatus(int id, Status status) {
        if (statusRepo.existsById(id)) {
            status.setId(id);
            return statusRepo.save(status);
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteStatus(int id) {
        if (statusRepo.existsById(id)) {
            statusRepo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
