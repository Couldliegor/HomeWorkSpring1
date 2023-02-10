package com.egorreceipe.receiptapp.Service;

import com.egorreceipe.receiptapp.Model.Ingridient;
import com.egorreceipe.receiptapp.Service.impl.IngridServicesImpl;

public interface IngridServices {
    void addIngridient(String name, int countOfIngridients, String typeOfUnit);
    Ingridient getIngrid(Integer id2);

    void checkingForIlligalArduments(String name, int countOfIngridients, String typeOfUnit);
}
