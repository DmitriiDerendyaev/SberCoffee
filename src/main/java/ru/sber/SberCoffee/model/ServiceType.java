package ru.sber.SberCoffee.model;

import jakarta.persistence.Id;

public class ServiceType {
    @Id
    private int id;
    private String name;
}
