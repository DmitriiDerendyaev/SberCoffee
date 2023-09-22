package ru.sber.SberCoffee.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sber.SberCoffee.entity.Status;
import ru.sber.SberCoffee.service.StatusService;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/status")
@RequiredArgsConstructor
public class StatusController {

    private final StatusService statusService;

    @GetMapping
    public ResponseEntity<List<Status>> getAllStatus() {
        List<Status> statusList = statusService.getAllStatus();
        if (statusList.isEmpty()) {
            log.warn("List of statuses is empty");
            return ResponseEntity.notFound().build();
        }
        log.info("List of statuses sent successfully {} ", statusList);
        return ResponseEntity.ok(statusList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Status> getStatusById(@PathVariable int id) {
        Optional<Status> statusOptional = statusService.getStatusById(id);

        if (statusOptional.isPresent()) {
            log.info("Requested status {}", statusOptional.get());
            return ResponseEntity.ok(statusOptional.get());
        } else {
            log.warn("Status doesn't exist. Requested statusId: {}", id);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Status> createStatus(@Valid @RequestBody Status status) {
        if (status == null) {
            log.warn("Sent body is null");
            return ResponseEntity.badRequest().build();
        }

        Status createdStatus = statusService.createStatus(status);
        if (createdStatus != null) {
            log.info("Status created successfully: {}", createdStatus);
            return ResponseEntity.ok(createdStatus);
        } else {
            log.error("Failed to create status");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Status> updateStatus(@PathVariable int id,@Valid @RequestBody Status status) {
        Status updatedStatus = statusService.updateStatus(id, status);

        if (updatedStatus != null) {
            log.info("Status updated successfully: {}", updatedStatus);
            return ResponseEntity.ok(updatedStatus);
        } else {
            log.warn("Status with ID {} not found. Update failed.", id);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStatus(@PathVariable int id) {
        boolean deleted = statusService.deleteStatus(id);
        if (deleted) {
            log.info("Status with ID {} deleted successfully", id);
            return ResponseEntity.noContent().build();
        } else {
            log.warn("Status with ID {} not found. Deletion failed.", id);
            return ResponseEntity.notFound().build();
        }
    }
}
