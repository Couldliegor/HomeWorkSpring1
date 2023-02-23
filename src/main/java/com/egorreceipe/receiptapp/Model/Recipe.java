package com.egorreceipe.receiptapp.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data // иквалс хешкод гет сет
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {
    private String name;
    private int posCountCookingInMin;
    private Integer[] ingredients;
    private List<String> steps;
}
