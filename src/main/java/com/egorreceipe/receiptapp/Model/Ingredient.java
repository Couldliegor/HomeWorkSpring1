package com.egorreceipe.receiptapp.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Ingredient {
    private  String name;
    private int countOfIngridients;
    private String typeOfUnit;
}

