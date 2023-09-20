package ru.sber.SberCoffee;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.sber.SberCoffee.entity.Item;
import ru.sber.SberCoffee.entity.ItemPrice;
import ru.sber.SberCoffee.entity.Size;
import ru.sber.SberCoffee.service.ItemPriceService;
import ru.sber.SberCoffee.service.ItemService;
import ru.sber.SberCoffee.service.SizeService;

@SpringBootApplication
@RequiredArgsConstructor
@EntityScan(basePackages = "ru.sber.SberCoffee.entity")
@EnableJpaRepositories(basePackages = "ru.sber.SberCoffee.repository")
public class SberCoffeeApplication {


	public static void main(String[] args) {
		SpringApplication.run(SberCoffeeApplication.class, args);

	}

}
