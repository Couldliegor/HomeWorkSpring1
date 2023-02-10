package com.egorreceipe.receiptapp.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController // аннотация спринг веб(модуль)
//даже есть обозначения зерна(боба) внизу.
public class StartController { // этот контроллер и есть бин.
    @GetMapping("/")
    public String helloApp() {
        return "Приложение запущено";
    }
    //инструкции.
    @GetMapping("/info")
    public String getInfo(String name) {
        return "имя ученика " + name + ", Название проекта: ReceiptAppForStudents " +
               ",Дата: " + LocalDate.now() + ",О проекте: Проект создан для студентов кулинарного вуза " +
               " ,Используем протокол передачи данных http, стиль REST, а также формат обменна данными json.";
    }

    // контроллеры обрабатывают http запросы

}
