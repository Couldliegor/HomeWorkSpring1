package com.egorreceipe.receiptapp.Service.impl;

import com.egorreceipe.receiptapp.Model.Ingridient;
import com.egorreceipe.receiptapp.Model.Recipe;
import com.egorreceipe.receiptapp.Service.ReceiptService;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class ReceiptServicessimpl implements ReceiptService {
    private List<Ingridient> ingridients = new ArrayList<>();
    private List<String> stepsToCookProperly = new ArrayList<>();
    public static Map<Integer, Recipe> receiptsInMap = new LinkedHashMap<>();
    public static Integer id = 0;

    @Override
    public Integer addRecipe(Recipe recipe) {
        checkingForIllegalArguments(recipe);
        receiptsInMap.put(id, recipe);
        return id++;
    }

    @Override
    public Recipe getRecipe(Integer id2) {
        return receiptsInMap.get(id2);
    }
    @Override
    public void checkingForIllegalArguments(Recipe recipe) {
        if (recipe.getPosCountCookingInMin()< 0) {
            throw new IllegalArgumentException("Неправильно введено количество ингридиентов");
        }
        if (recipe.getName().isEmpty() || recipe.getName().isBlank()) {
            throw new IllegalArgumentException("Имя ингридиента не может быть пустым");
        }
    }

    @Override
    public boolean editRecipe(int id, Recipe recipe) {
        if (!receiptsInMap.get(id).toString().isEmpty()) {
            receiptsInMap.put(id, recipe);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteRecipe(int id) {
        if (!receiptsInMap.get(id).toString().isEmpty()) {
            receiptsInMap.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public Map<Integer, Recipe> getAllRecipes() {
        return receiptsInMap;
    }
}
