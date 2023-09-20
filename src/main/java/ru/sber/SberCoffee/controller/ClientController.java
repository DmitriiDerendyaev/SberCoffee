package ru.sber.SberCoffee.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sber.SberCoffee.service.ClientService;

@RestController
@RequestMapping("client/")
public class ClientController {


    ClientService clientService;

    public void getClient(){
//        clientService.save();
    }

}
