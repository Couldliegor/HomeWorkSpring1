package com.egorreceipe.receiptapp.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Ingridient {
    private final String name;
    private final int countOfIngridients;
    private final String typeOfUnit;
}

