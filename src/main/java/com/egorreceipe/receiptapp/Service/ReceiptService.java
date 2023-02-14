package com.egorreceipe.receiptapp.Service;

import com.egorreceipe.receiptapp.Model.Recipe;

import java.util.Map;

public interface ReceiptService {
    Integer addRecipe(Recipe recipe);
    Recipe getRecipe(Integer id);
    void checkingForIllegalArguments(Recipe recipe);

    boolean editRecipe(int id, Recipe recipe);

    boolean deleteRecipe(int id);
    Map<Integer, Recipe> getAllRecipes();
}
