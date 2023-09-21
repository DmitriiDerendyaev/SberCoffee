package ru.sber.SberCoffee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class StaffRequestDTO {
    private String name;
    private String surname;
    private String patronymic;
    private int position;
    private String phoneNumber;
    private String address;

}
