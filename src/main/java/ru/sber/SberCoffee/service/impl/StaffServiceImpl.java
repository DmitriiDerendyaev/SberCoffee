package ru.sber.SberCoffee.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sber.SberCoffee.entity.Staff;
import ru.sber.SberCoffee.repository.StaffRepo;
import ru.sber.SberCoffee.service.StaffService;

import java.util.List;
import java.util.Optional;

/**
 * The type Staff service.
 */
@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {

    private final StaffRepo staffRepo;

    @Override
    public List<Staff> getAllStaff() {
        return staffRepo.findAll();
    }

    @Override
    public Optional<Staff> getStaffById(int id) {
        return staffRepo.findById(id);
    }

    @Override
    public Staff createStaff(Staff staff) {
        return staffRepo.save(staff);
    }

    @Override
    public Staff updateStaff(int id, Staff newStaff) {
        if (staffRepo.existsById(id)) {
            newStaff.setId(id);
            return staffRepo.save(newStaff);
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteStaff(int id) {
        if (staffRepo.existsById(id)) {
            staffRepo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}