package ru.sber.SberCoffee.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sber.SberCoffee.dto.StaffResponseDTO;
import ru.sber.SberCoffee.entity.Position;
import ru.sber.SberCoffee.entity.Staff;
import ru.sber.SberCoffee.service.PositionService;
import ru.sber.SberCoffee.service.StaffService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/staff")
@RequiredArgsConstructor
public class StaffController {

    private final StaffService staffService;
    private final PositionService positionService;

    @GetMapping
    public ResponseEntity<List<StaffResponseDTO>> getAllStaff() {
        List<Staff> staffList = staffService.getAllStaff();
        if (staffList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<StaffResponseDTO> responseDTOList = staffList.stream()
                .map(this::mapStaffToStaffDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responseDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StaffResponseDTO> getStaffById(@PathVariable int id) {
        Optional<Staff> staffOptional = staffService.getStaffById(id);

        if (staffOptional.isPresent()) {
            StaffResponseDTO responseDTO = mapStaffToStaffDTO(staffOptional.get());
            return ResponseEntity.ok(responseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<StaffResponseDTO> createStaff(@RequestBody Staff staff) {
        if (staff == null) {
            return ResponseEntity.badRequest().build();
        }

        Staff createdStaff = staffService.createStaff(staff);
        StaffResponseDTO responseDTO = mapStaffToStaffDTO(createdStaff);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StaffResponseDTO> updateStaff(@PathVariable int id, @RequestBody Staff newStaff) {
        if (newStaff == null) {
            return ResponseEntity.badRequest().build();
        }

        Staff updatedStaff = staffService.updateStaff(id, newStaff);
        if (updatedStaff != null) {
            StaffResponseDTO responseDTO = mapStaffToStaffDTO(updatedStaff);
            return ResponseEntity.ok(responseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStaff(@PathVariable int id) {
        boolean deleted = staffService.deleteStaff(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private StaffResponseDTO mapStaffToStaffDTO(Staff staff) {
        return new StaffResponseDTO(
                staff.getId(),
                staff.getName(),
                staff.getSurname(),
                staff.getPatronymic(),
                staff.getPosition().getName(),
                staff.getPhoneNumber(),
                staff.getAddress()
        );
    }
}
