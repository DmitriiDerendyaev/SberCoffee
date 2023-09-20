package ru.sber.SberCoffee.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.sber.SberCoffee.entity.Size;

import java.util.List;

@Data
@RequiredArgsConstructor
@Component
public class ItemDTO {
    private int item_id;
    private String name;
    private double basePrice;
    private List<Long> sizes;
    private List<Long> modifiers;

}
