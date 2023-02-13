package com.egorreceipe.receiptapp.Service;

import com.egorreceipe.receiptapp.Model.Ingridient;

import java.util.Map;

public interface IngridServices {
    Integer addIngridient(Ingridient ingridient);

    Ingridient getIngrid(Integer id2);

    void checkingForIlligalArduments(Ingridient ingridient);

    boolean editIngridient(int id, Ingridient ingridient);

    void deleteIngridient(int id);

    Map<Integer, Ingridient> getAllIngridients();
}
