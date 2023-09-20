package ru.sber.SberCoffee.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ItemPrice {
    @Id
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
    @Id
    @ManyToOne
    @JoinColumn(name = "size_id")
    private Size size;
}
