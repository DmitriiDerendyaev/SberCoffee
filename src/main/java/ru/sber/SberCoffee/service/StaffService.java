package ru.sber.SberCoffee.service;

import ru.sber.SberCoffee.entity.Staff;

import java.util.List;
import java.util.Optional;

/**
 * The interface Staff service.
 */
public interface StaffService {
    /**
     * Gets all staff.
     *
     * @return the all staff
     */
    List<Staff> getAllStaff();

    /**
     * Gets staff by id.
     *
     * @param id the id
     * @return the staff by id
     */
    Optional<Staff> getStaffById(int id);

    /**
     * Create staff staff.
     *
     * @param staff the staff
     * @return the staff
     */
    Staff createStaff(Staff staff);

    /**
     * Update staff staff.
     *
     * @param id       the id
     * @param newStaff the new staff
     * @return the staff
     */
    Staff updateStaff(int id, Staff newStaff);

    /**
     * Delete staff boolean.
     *
     * @param id the id
     * @return the boolean
     */
    boolean deleteStaff(int id);
}
