package ru.sber.SberCoffee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.sber.SberCoffee.entity.Position;
import ru.sber.SberCoffee.entity.Staff;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class StaffDTO {
    private int id;
    private String name;
    private String surname;
    private String patronymic;
    private Position position;
    private Staff reportTo;
    private String phoneNumber;
    private String address;
}
