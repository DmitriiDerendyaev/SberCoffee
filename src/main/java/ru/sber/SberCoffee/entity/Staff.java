package ru.sber.SberCoffee.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull(message = "Name cannot be null")
    private String name;
    @NotNull(message = "Surname cannot be null")
    private String surname;
    private String patronymic;
    @NotNull(message = "Position cannot be null")
    @ManyToOne
    @JoinColumn(name = "position")
    private Position position;
    @NotNull(message = "Phone number cannot be null")
    @Pattern(regexp = "\\d{11}", message = "Phone number must be a 10-digit number")
    private String phoneNumber;
    @NotNull(message = "Address cannot be null")
    private String address;
}
