package ru.sber.SberCoffee.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sber.SberCoffee.dto.StaffRequestDTO;
import ru.sber.SberCoffee.dto.StaffResponseDTO;
import ru.sber.SberCoffee.entity.Position;
import ru.sber.SberCoffee.entity.Staff;
import ru.sber.SberCoffee.service.PositionService;
import ru.sber.SberCoffee.service.StaffService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The type Staff controller.
 */
@Slf4j
@RestController
@RequestMapping("/staff")
@RequiredArgsConstructor
public class StaffController {

    private final StaffService staffService;
    private final PositionService positionService;

    /**
     * Gets all staff.
     *
     * @return the all staff
     */
    @GetMapping
    public ResponseEntity<List<StaffResponseDTO>> getAllStaff() {
        List<Staff> staffList = staffService.getAllStaff();
        if (staffList.isEmpty()) {
            log.warn("List of staff is empty");
            return ResponseEntity.notFound().build();
        }

        List<StaffResponseDTO> responseDTOList = staffList.stream()
                .map(this::mapStaffToStaffDTO)
                .collect(Collectors.toList());

        log.info("Retrieved {} staff members", staffList.size());
        return ResponseEntity.ok(responseDTOList);
    }

    /**
     * Gets staff by id.
     *
     * @param id the id
     * @return the staff by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<StaffResponseDTO> getStaffById(@PathVariable int id) {
        Optional<Staff> staffOptional = staffService.getStaffById(id);

        if (staffOptional.isPresent()) {
            log.info("Retrieved staff by ID: {}", id);
            StaffResponseDTO responseDTO = mapStaffToStaffDTO(staffOptional.get());
            return ResponseEntity.ok(responseDTO);
        } else {
            log.warn("Staff with ID {} not found", id);
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Create staff response entity.
     *
     * @param staffRequestDTO the staff request dto
     * @return the response entity
     */
    @PostMapping
    public ResponseEntity<StaffResponseDTO> createStaff(@Valid @RequestBody StaffRequestDTO staffRequestDTO) {
        if (staffRequestDTO == null) {
            log.warn("Sent body is null");
            return ResponseEntity.badRequest().build();
        }

        Optional<Position> position = positionService.getPositionById(staffRequestDTO.getPosition());
        if (!position.isPresent()) {
            log.warn("Invalid position ID: {}", staffRequestDTO.getPosition());
            return ResponseEntity.badRequest().build();
        }

        Staff staff = Staff.builder()
                .name(staffRequestDTO.getName())
                .surname(staffRequestDTO.getSurname())
                .patronymic(staffRequestDTO.getPatronymic())
                .position(position.orElse(null))
                .phoneNumber(staffRequestDTO.getPhoneNumber())
                .address(staffRequestDTO.getAddress())
                .build();

        Staff createdStaff = staffService.createStaff(staff);
        StaffResponseDTO responseDTO = mapStaffToStaffDTO(createdStaff);

        log.info("Created staff member: {}", responseDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    /**
     * Update staff response entity.
     *
     * @param id              the id
     * @param staffRequestDTO the staff request dto
     * @return the response entity
     */
    @PutMapping("/{id}")
    public ResponseEntity<StaffResponseDTO> updateStaff(@PathVariable int id, @Valid @RequestBody StaffRequestDTO staffRequestDTO) {
        if (staffRequestDTO == null) {
            log.warn("Sent body is null");
            return ResponseEntity.badRequest().build();
        }

        Optional<Position> position = positionService.getPositionById(staffRequestDTO.getPosition());
        if (!position.isPresent()) {
            log.warn("Invalid position ID: {}", staffRequestDTO.getPosition());

            return ResponseEntity.badRequest().build();
        }

        Staff newStaff = Staff.builder()
                .name(staffRequestDTO.getName())
                .surname(staffRequestDTO.getSurname())
                .patronymic(staffRequestDTO.getPatronymic())
                .position(position.orElse(null))
                .phoneNumber(staffRequestDTO.getPhoneNumber())
                .address(staffRequestDTO.getAddress())
                .build();

        Staff updatedStaff = staffService.updateStaff(id, newStaff);
        if (updatedStaff != null) {
            StaffResponseDTO responseDTO = mapStaffToStaffDTO(updatedStaff);
            log.info("Updated staff member: {}", responseDTO);
            return ResponseEntity.ok(responseDTO);
        } else {
            log.warn("Staff with ID {} not found. Update failed.", id);
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Delete staff response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStaff(@PathVariable int id) {
        boolean deleted = staffService.deleteStaff(id);
        if (deleted) {
            log.info("Deleted staff member with ID: {}", id);
            return ResponseEntity.noContent().build();
        } else {
            log.warn("Staff with ID {} not found. Deletion failed.", id);
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
