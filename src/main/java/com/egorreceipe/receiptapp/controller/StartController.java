package com.egorreceipe.receiptapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class StartController {

    Date data = new Date();
    @GetMapping("/")
    public String helloApp() {
        return "Приложение запущено";
    }

    @GetMapping("/info")
    public String getInfo(String name) {
        return "имя ученика " + name + ", Название проекта: ReceiptAppForStudents " +
               ",Дата: " + data + ",О проекте: Проект создан для студентов кулинарного вуза " +
               " ,Используем протокол передачи данных http, стиль REST, а также формат обменна данными json.";
    }

}
