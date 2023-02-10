package com.egorreceipe.receiptapp.Controller;


import com.egorreceipe.receiptapp.Service.IngridServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ingrid")
public class IngridientsController {
    private IngridServices ingridServices;
    public IngridientsController(IngridServices ingridServices) {
        this.ingridServices = ingridServices;
    }
    @GetMapping("/add")
    public String addIngrid(@RequestParam String name, @RequestParam int countOfIngridients, @RequestParam String typeOfUnit) {
        ingridServices.addIngridient(name,countOfIngridients, typeOfUnit);
        return "Ингридиент добавлен";
    }
    @GetMapping("/get")
    public String getIngrid(@RequestParam int id2) {
        return ingridServices.getIngrid(id2).toString();
    }
}
