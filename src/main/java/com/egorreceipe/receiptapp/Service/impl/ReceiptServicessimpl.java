package com.egorreceipe.receiptapp.Service.impl;

import com.egorreceipe.receiptapp.Model.Ingridient;
import com.egorreceipe.receiptapp.Model.Recipe;
import com.egorreceipe.receiptapp.Service.ReceiptService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReceiptServicessimpl implements ReceiptService {
    @Override
    public void addRecipe(String name, int posCountCookingInMin, List<Ingridient> ingridients, List<String> stepsToCookProperly) {
        checkingForIlligalArduments(name, posCountCookingInMin);
        Recipe recipe = new Recipe(name, posCountCookingInMin, ingridients, stepsToCookProperly);
    }

    @Override
    public Recipe getRecipe(Integer id2) {
        return Recipe.receiptsInMap.get(id2);
    }

    @Override
    public String toString() {
        return Recipe.receiptsInMap.toString();
    }

    @Override
    public void checkingForIlligalArduments(String name, int posCountCookingInMin) {
        if (posCountCookingInMin < 0) {
            throw new IllegalArgumentException("Неправильно введено количество ингридиентов");
        }
        if (name.isEmpty() || name.isBlank()) {
            throw new IllegalArgumentException("Имя ингридиента не может быть пустым");
        }
    }
}
