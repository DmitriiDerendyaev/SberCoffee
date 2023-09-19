package ru.sber.SberCoffee.model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    @Id
    private Long id;
    private String name;
    private String surname;
    private String patronymic;
    private int phoneNumber;
    private String address;
    private String email;
    private Date birthday;
}
