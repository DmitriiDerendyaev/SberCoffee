package ru.sber.SberCoffee.model;

import jakarta.persistence.Id;

public class Status {
    @Id
    private int id;
    private String name;
}
