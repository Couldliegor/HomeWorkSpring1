package com.egorreceipe.receiptapp.Service;

import com.egorreceipe.receiptapp.Model.Ingridient;
import com.egorreceipe.receiptapp.Model.Recipe;

import java.util.List;

public interface ReceiptService {

    public void addRecipe(String name, int countOfIngridients, List<Ingridient> ingridients, List<String> stepsToCookProperly);

    Recipe getRecipe(Integer id2);

    void checkingForIlligalArduments(String name, int posCountCookingInMin);
}
