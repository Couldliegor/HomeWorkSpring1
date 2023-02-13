package com.egorreceipe.receiptapp.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data // иквалс хешкод гет сет
@AllArgsConstructor
public class Recipe {
    private String name;
    private int posCountCookingInMin;
}
