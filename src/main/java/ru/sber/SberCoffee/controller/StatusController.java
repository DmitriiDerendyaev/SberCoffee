package ru.sber.SberCoffee.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sber.SberCoffee.entity.Status;
import ru.sber.SberCoffee.service.StatusService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/status")
@RequiredArgsConstructor
public class StatusController {

    private final StatusService statusService;

    @GetMapping
    public ResponseEntity<List<Status>> getAllStatus() {
        List<Status> statusList = statusService.getAllStatus();
        if (statusList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(statusList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Status> getStatusById(@PathVariable int id) {
        Optional<Status> statusOptional = statusService.getStatusById(id);

        if (statusOptional.isPresent()) {
            return ResponseEntity.ok(statusOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Status> createStatus(@RequestBody Status status) {
        if (status == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(statusService.createStatus(status));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Status> updateStatus(@PathVariable int id, @RequestBody Status status) {
        Status updatedStatus = statusService.updateStatus(id, status);

        if (updatedStatus != null) {
            return ResponseEntity.ok(updatedStatus);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStatus(@PathVariable int id) {
        if (statusService.deleteStatus(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
