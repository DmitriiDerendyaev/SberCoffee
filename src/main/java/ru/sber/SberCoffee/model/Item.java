package ru.sber.SberCoffee.model;

import jakarta.persistence.Id;

public class Item {
    @Id
    private int id;
    private String name;
    private double basePrice;
}
