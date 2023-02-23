package com.egorreceipe.receiptapp.Service;

import com.egorreceipe.receiptapp.Model.ConnectedRecipe;
import com.egorreceipe.receiptapp.Model.Recipe;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public interface ReceiptService {
    Path createRecipeFile() throws IOException;
    Integer addRecipe(Recipe recipe);
    ConnectedRecipe getRecipe(Integer id);
    void checkingForIllegalArguments(Recipe recipe);
    boolean editRecipe(int id, ConnectedRecipe recipe);
    boolean deleteRecipe(int id);
    Map<Integer, ConnectedRecipe> getAllRecipes();

    void saveToFile();
}
