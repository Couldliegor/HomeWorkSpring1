package com.egorreceipe.receiptapp.Controller;
import com.egorreceipe.receiptapp.Model.Ingridient;
import com.egorreceipe.receiptapp.Service.ReceiptService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recipe") // обработка методов будет начинаться с budget.
public class ReceiptController {
    private ReceiptService receiptService;

    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @GetMapping("/add")
    public String addReceipe(@RequestParam String name, @RequestParam int posCountCookingInMin, @RequestParam List<String> stepsToCookProperly, @RequestParam List<Ingridient> ingridients) {
        receiptService.addRecipe(name, posCountCookingInMin, ingridients, stepsToCookProperly);
        return "Рецепт успешно добавлен";
    }
    @GetMapping("/get")
    public String getReceipe(@RequestParam Integer id2) {
        return receiptService.getRecipe(id2).toString();
    }
}
