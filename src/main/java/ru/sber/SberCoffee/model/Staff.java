package ru.sber.SberCoffee.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Staff {
    @Id
    private int id;
    private String name;
    private String surname;
    private String patronymic;

    private Position position;

    private Staff reportTo;

    private String phoneNumber;
    private String address;
}
