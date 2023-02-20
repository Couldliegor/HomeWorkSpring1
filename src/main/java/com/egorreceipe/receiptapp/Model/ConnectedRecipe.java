package com.egorreceipe.receiptapp.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.LinkedHashMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConnectedRecipe {
    private String name;
    private int posCountCookingInMin;
    private LinkedHashMap<Integer,String> steps;
    private ArrayList<Ingredient> ingredients;
}
