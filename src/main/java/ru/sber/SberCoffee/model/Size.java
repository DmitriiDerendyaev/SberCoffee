package ru.sber.SberCoffee.model;

import jakarta.persistence.Id;

public class Size {
    @Id
    private int id;
    private String name;
    private double priceModifier;
}
