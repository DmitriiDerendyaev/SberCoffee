package ru.sber.SberCoffee.service;

import ru.sber.SberCoffee.entity.Staff;

import java.util.List;
import java.util.Optional;

public interface StaffService {
    List<Staff> getAllStaff();
    Optional<Staff> getStaffById(int id);
    Staff createStaff(Staff staff);
    Staff updateStaff(int id, Staff newStaff);
    boolean deleteStaff(int id);
}
