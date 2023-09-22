package ru.sber.SberCoffee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * The type Staff response dto.
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class StaffResponseDTO {
    private int id;
    private String name;
    private String surname;
    private String patronymic;
    private String position;
    private String phoneNumber;
    private String address;
}
