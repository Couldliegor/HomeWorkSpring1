package com.egorreceipe.receiptapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// запускает Spring.
@SpringBootApplication // используем модуль Spring boot
public class ReceiptAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReceiptAppApplication.class, args);
    }
}
