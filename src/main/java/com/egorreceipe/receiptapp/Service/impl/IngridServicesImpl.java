package com.egorreceipe.receiptapp.Service.impl;

import com.egorreceipe.receiptapp.Model.Ingridient;
import com.egorreceipe.receiptapp.Service.IngridServices;
import org.springframework.stereotype.Service;

import static com.egorreceipe.receiptapp.Model.Ingridient.ingridInMap;

@Service
public class IngridServicesImpl implements IngridServices {
    @Override
    public void addIngridient(String name, int countOfIngridients, String typeOfUnit) {
        checkingForIlligalArduments(name, countOfIngridients, typeOfUnit);
        Ingridient ingridient = new Ingridient(name, countOfIngridients, typeOfUnit);
    }
    @Override
    public Ingridient getIngrid(Integer id2) {
        return ingridInMap.get(id2);
    }
    public String toString() {
        return Ingridient.ingridInMap.toString();
    }

    @Override
    public void checkingForIlligalArduments(String name, int countOfIngridients, String typeOfUnit) {
        if (countOfIngridients < 0) {
            throw new IllegalArgumentException("Неправильно введено количество ингридиентов");
        }
        if (name.isEmpty() || name.isBlank()) {
            throw new IllegalArgumentException("Имя ингридиента не может быть пустым");
        }
        if (typeOfUnit.isBlank() || typeOfUnit.isEmpty()) {
            throw new IllegalArgumentException("Единица измерения не может быть пустой");
        }
    }
}
