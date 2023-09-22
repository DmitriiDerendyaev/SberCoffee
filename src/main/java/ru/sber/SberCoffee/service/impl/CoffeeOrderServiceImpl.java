package ru.sber.SberCoffee.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sber.SberCoffee.entity.CoffeeOrder;
import ru.sber.SberCoffee.repository.CoffeeOrderRepo;
import ru.sber.SberCoffee.service.CoffeeOrderService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CoffeeOrderServiceImpl implements CoffeeOrderService {

    private final CoffeeOrderRepo coffeeOrderRepo;
    

    @Override
    public List<CoffeeOrder> getAllCoffeeOrders() {
        return coffeeOrderRepo.findAll();
    }

    @Override
    public Optional<CoffeeOrder> getCoffeeOrderById(Long id) {
        return coffeeOrderRepo.findById(id);
    }

    @Override
    public CoffeeOrder createCoffeeOrder(CoffeeOrder coffeeOrder) {
        return coffeeOrderRepo.save(coffeeOrder);
    }

    @Override
    public CoffeeOrder updateCoffeeOrder(Long id, CoffeeOrder coffeeOrder) {
        if (coffeeOrderRepo.existsById(id)) {
            coffeeOrder.setId(id);
            return coffeeOrderRepo.save(coffeeOrder);
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteCoffeeOrder(Long id) {
        if (coffeeOrderRepo.existsById(id)) {
            coffeeOrderRepo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
