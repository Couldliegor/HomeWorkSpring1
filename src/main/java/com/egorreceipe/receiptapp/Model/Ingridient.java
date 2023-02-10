package com.egorreceipe.receiptapp.Model;

import lombok.Data;
import lombok.experimental.StandardException;

import java.util.LinkedHashMap;
import java.util.Map;

@Data
public class Ingridient {
    private String name;
    private int countOfIngridients;
    private String typeOfUnit;
    private static int id2 = 1;
    public static Map<Integer, Ingridient> ingridInMap = new LinkedHashMap<>();

    public Ingridient(String name, int countOfIngridients, String typeOfUnit) {
        this.name = name;
        this.countOfIngridients = countOfIngridients;
        this.typeOfUnit = typeOfUnit;
        ingridInMap.put(id2, this);
        id2++;
    }
}
