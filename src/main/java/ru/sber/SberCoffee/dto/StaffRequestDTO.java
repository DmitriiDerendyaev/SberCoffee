package ru.sber.SberCoffee.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * The type Staff request dto.
 */
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class StaffRequestDTO {
    @NotNull(message = "Name cannot be null")
    private String name;
    @NotNull(message = "Surname cannot be null")
    private String surname;
    private String patronymic;
    @NotNull(message = "Position ID cannot be null")
    @Positive(message = "Position ID must be a positive number")
    private int position;
    @NotNull(message = "Phone number cannot be null")
    @Pattern(regexp = "\\d{11}", message = "Phone number must be a 10-digit number")
    private String phoneNumber;
    @NotNull(message = "Address cannot be null")
    private String address;

}
