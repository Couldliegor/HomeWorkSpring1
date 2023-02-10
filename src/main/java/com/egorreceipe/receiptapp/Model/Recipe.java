package com.egorreceipe.receiptapp.Model;

import com.egorreceipe.receiptapp.Service.impl.ReceiptServicessimpl;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Data // иквалс хешкод гет сет
public class Recipe {
    private String name;
    private int posCountCookingInMin;
    private List<Ingridient> ingridients = new ArrayList<>();
    private List<String> stepsToCookProperly = new ArrayList<>();
    public static Map<Integer, Recipe> receiptsInMap = new LinkedHashMap<>();
    private static int id2 = 0;
    public Recipe(String name, int posCountCookingInMin, List<Ingridient> ingridients, List<String> stepsToCookProperly) {
        this.name = name;
        this.posCountCookingInMin = posCountCookingInMin;
        this.ingridients = ingridients;
        this.stepsToCookProperly = stepsToCookProperly;
        receiptsInMap.put(id2, this);
        id2++;
    }
}
