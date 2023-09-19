package ru.sber.SberCoffee.model;

import jakarta.persistence.Id;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class Order {
    @Id
    private Long id;
    private int position;
    private Long clientId;
    private int itemId;
    private int quantity;
    private int sizeId;
    private int serviceTypeId;
    private int statusId;
    private Long staffId;
    private ZonedDateTime startTime;
    private ZonedDateTime endTime;
    private double total;

}
