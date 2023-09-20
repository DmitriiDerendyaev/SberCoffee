package ru.sber.SberCoffee.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoffeeOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client clientId;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item itemId;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "size_id")
    private Size sizeId;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status statusId;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff staffId;
    private ZonedDateTime startTime;
    private ZonedDateTime endTime;

    private Double total;

}
