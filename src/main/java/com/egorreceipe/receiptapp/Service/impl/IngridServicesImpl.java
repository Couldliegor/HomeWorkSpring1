package com.egorreceipe.receiptapp.Service.impl;

import com.egorreceipe.receiptapp.Model.Ingridient;
import com.egorreceipe.receiptapp.Service.IngridServices;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;


@Service
public class IngridServicesImpl implements IngridServices {
    public static Map<Integer, Ingridient> ingridInMap = new LinkedHashMap<>();
    public static int id = 0;
    @Override
    public Integer addIngridient(Ingridient ingridient) {
        checkingForIlligalArduments(ingridient);
        ingridInMap.put(id, ingridient);
        return id++;
    }
    @Override
    public Ingridient getIngrid(Integer id) {
        if (ingridInMap.get(id).toString().isEmpty()) {
            return null;
        } else {
            return ingridInMap.get(id);
        }
    }
    @Override
    public void checkingForIlligalArduments(Ingridient ingridient) {
        if (ingridient.getCountOfIngridients() < 0) {
            throw new IllegalArgumentException("Неправильно введено количество ингридиентов");
        }
        if (ingridient.getName().isEmpty() || ingridient.getName().isBlank()) {
            throw new IllegalArgumentException("Имя ингридиента не может быть пустым");
        }
        if (ingridient.getTypeOfUnit().isBlank() || ingridient.getTypeOfUnit().isEmpty()) {
            throw new IllegalArgumentException("Единица измерения не может быть пустой");
        }
    }

    @Override
    public boolean editIngridient(int id, Ingridient ingridient) {
        if (!ingridInMap.get(id).toString().isEmpty()) {
            ingridInMap.put(id, ingridient);
            return true;
        }
            return false;
    }

    @Override
    public void deleteIngridient(int id) {
            ingridInMap.remove(id);
    }

    @Override
    public Map<Integer, Ingridient> getAllIngridients() {
        return ingridInMap;
    }
}
