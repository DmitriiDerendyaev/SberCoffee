package ru.sber.SberCoffee.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * The type Position.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Position {

    /**
     * Instantiates a new Position.
     *
     * @param id   the id
     * @param name the name
     */
    public Position(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Position name is required")
    private String name;
    private String description;
}
