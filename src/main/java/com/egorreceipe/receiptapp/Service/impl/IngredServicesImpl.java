package com.egorreceipe.receiptapp.Service.impl;

import com.egorreceipe.receiptapp.Model.Ingredient;
import com.egorreceipe.receiptapp.Service.FilesRecipeService;
import com.egorreceipe.receiptapp.Service.IngridServices;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.LinkedHashMap;
import java.util.Map;


@Service
public class IngredServicesImpl implements IngridServices {
    public static Map<Integer, Ingredient> ingredInMap = new LinkedHashMap<>();
    private static int id = 0;
    private final FilesRecipeService filesServices;

    public IngredServicesImpl(FilesRecipeService filesServices) {
        this.filesServices = filesServices;
    }

    @PostConstruct
    private void init() {
        try {
            if (!filesServices.readFromFile().isEmpty()) {
                readFromFile();
            }
        } catch (Exception e) {
            saveToFile();
            e.printStackTrace();
        }
    }
    @Override
    public Integer addIngridient(Ingredient ingredient) {
        checkingForIlligalArduments(ingredient);
        ingredInMap.put(id, ingredient);
        saveToFile();
        return id++;
    }
    @Override
    public Ingredient getIngrid(Integer id) {
        return ingredInMap.get(id);
    }
    @Override //String utils
    public void checkingForIlligalArduments(Ingredient ingredient) {
        if (ingredient.getCountOfIngridients() < 0) {
            throw new IllegalArgumentException("Неправильно введено количество ингридиентов");
        }
        if (StringUtils.isBlank(ingredient.getName()) || StringUtils.isEmpty(ingredient.getName())) {
            throw new IllegalArgumentException("Имя ингридиента не может быть пустым");
        }
        if (StringUtils.isBlank(ingredient.getTypeOfUnit()) || StringUtils.isEmpty(ingredient.getTypeOfUnit())) {
            throw new IllegalArgumentException("Единица измерения не может быть пустой");
        }
    }

    @Override
    public boolean editIngridient(int id, Ingredient ingredient) {
        if (ingredInMap.get(id) != null) {
            ingredInMap.put(id, ingredient);
            saveToFile();
            return true;
        }
            return false;
    }

    @Override
    public void deleteIngridient(int id) {
            ingredInMap.remove(id);
            saveToFile();
    }

    @Override
    public Map<Integer, Ingredient> getAllIngridients() {
        return ingredInMap;
    }
    @Override
    public void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(ingredInMap);
            filesServices.saveToFile(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private void readFromFile() {
        String json = filesServices.readFromFile();
        try {
            ingredInMap =  new ObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES).readValue(json, new TypeReference<LinkedHashMap<Integer, Ingredient>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
