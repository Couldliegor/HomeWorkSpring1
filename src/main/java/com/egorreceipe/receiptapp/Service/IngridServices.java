package com.egorreceipe.receiptapp.Service;

import com.egorreceipe.receiptapp.Model.Ingredient;

import java.util.Map;

public interface IngridServices {
    Integer addIngridient(Ingredient ingredient);

    Ingredient getIngrid(Integer id2);

    void checkingForIlligalArduments(Ingredient ingredient);

    boolean editIngridient(int id, Ingredient ingredient);

    void deleteIngridient(int id);

    Map<Integer, Ingredient> getAllIngridients();

    void saveToFile();
}
