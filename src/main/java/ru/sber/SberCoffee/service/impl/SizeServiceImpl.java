package ru.sber.SberCoffee.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sber.SberCoffee.entity.Size;
import ru.sber.SberCoffee.repository.SizeRepo;
import ru.sber.SberCoffee.service.SizeService;

@RequiredArgsConstructor
@Service
public class SizeServiceImpl implements SizeService {

    private SizeRepo sizeRepo;

    @Override
    public void save(Size size) {
        sizeRepo.save(size);
    }
}
