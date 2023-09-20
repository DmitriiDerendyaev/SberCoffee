package ru.sber.SberCoffee.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sber.SberCoffee.entity.Position;
import ru.sber.SberCoffee.entity.Staff;
import ru.sber.SberCoffee.repository.StaffRepo;
import ru.sber.SberCoffee.service.PositionService;
import ru.sber.SberCoffee.service.StaffService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/staff")
@RequiredArgsConstructor
public class StaffController {

    private final StaffService staffService;
    private final PositionService positionService;

    @GetMapping
    public List<Staff> getAllStaff() {
        return staffService.getAllStaff();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Staff> getStaffById(@PathVariable int id) {
        Optional<Staff> staff = staffService.getStaffById(id);
        return staff.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createStaff(@RequestBody Staff staff) {
        Optional<Position> position = positionService.getPositionById(staff.getPosition().getId());
        if (position.isEmpty()) {
            return ResponseEntity.badRequest().body("Position with id " + staff.getPosition().getId() + " does not exist.");
        }

        Staff createdStaff = staffService.createStaff(staff);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStaff);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStaff(@PathVariable int id, @RequestBody Staff newStaff) {
        Optional<Position> position = positionService.getPositionById(newStaff.getPosition().getId());
        if (position.isEmpty()) {
            return ResponseEntity.badRequest().body("Position with id " + newStaff.getPosition().getId() + " does not exist.");
        }

        Staff updatedStaff = staffService.updateStaff(id, newStaff);
        if (updatedStaff != null) {
            return ResponseEntity.ok(updatedStaff);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStaff(@PathVariable int id) {
        boolean deleted = staffService.deleteStaff(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

}
