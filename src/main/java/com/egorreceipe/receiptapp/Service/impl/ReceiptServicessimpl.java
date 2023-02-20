package com.egorreceipe.receiptapp.Service.impl;

import com.egorreceipe.receiptapp.Model.ConnectedRecipe;
import com.egorreceipe.receiptapp.Model.Ingredient;
import com.egorreceipe.receiptapp.Model.Recipe;
import com.egorreceipe.receiptapp.Service.FilesRecipeService;
import com.egorreceipe.receiptapp.Service.ReceiptService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
@Service
public class ReceiptServicessimpl implements ReceiptService {
    private final FilesRecipeService filesServices;
    private Map<Integer, ConnectedRecipe> receiptsInMap = new LinkedHashMap<>();
    private final Map<Integer, Ingredient> ingredInMap = IngredServicesImpl.ingredInMap;
    private static Integer id = 0;

    public ReceiptServicessimpl(@Qualifier("filesRecipeServiceImpl") FilesRecipeService filesServices) {
        this.filesServices = filesServices;
    }
    @PostConstruct // проблема тут
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
    public Integer addRecipe(Recipe recipe) {
        checkingForIllegalArguments(recipe);
        Integer[] ingredients = recipe.getIngredients();
        ArrayList<Ingredient> ingredients1 = new ArrayList<>();
        for (int i = 0; i < ingredients.length ; i++) {
            ingredients1.add(i,ingredInMap.get(ingredients[i]));
            if(ingredInMap.get(ingredients[i]) == null) throw new IllegalArgumentException("Ингридиента под таким айди не существует, пожалуйста, обновите значения Базы Данных");
        }
        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
        for (int i = 0; i < recipe.getSteps().size() ; i++) {
            linkedHashMap.put(i, recipe.getSteps().get(i));
        }
        ConnectedRecipe connectedRecipe = new ConnectedRecipe(recipe.getName(),
                recipe.getPosCountCookingInMin(),
                linkedHashMap,
                ingredients1);
        receiptsInMap.put(id, connectedRecipe);
        saveToFile();
        return id++;
    }

    @Override
    public ConnectedRecipe getRecipe(Integer id2) {
        return receiptsInMap.get(id2);
    }

    @Override
    public void checkingForIllegalArguments(Recipe recipe) {
        if (recipe.getPosCountCookingInMin() < 0) {
            throw new IllegalArgumentException("Неправильно введено время приготовления");
        }
        if (StringUtils.isEmpty(recipe.getName()) || StringUtils.isBlank(recipe.getName())) {
            throw new IllegalArgumentException("Имя рецепта не может быть пустым");
        }
        Integer[] ints = recipe.getIngredients();
        for (int i = 0; i < recipe.getIngredients().length ; i++) {
            if (ints[i] < 0) {
                throw new IllegalArgumentException("Ингридиента под таким номером нет, и быть не может!");
            }
        }
    }

    @Override
    public boolean editRecipe(int id, ConnectedRecipe recipe) {
        if (receiptsInMap.get(id) == null) {
            receiptsInMap.put(id, recipe);
            saveToFile();
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteRecipe(int id) {
        if (receiptsInMap.get(id) == null) {
            receiptsInMap.remove(id);
            saveToFile();
            return true;
        }
        return false;
    }
    @Override
    public Map<Integer, ConnectedRecipe> getAllRecipes() {
        return receiptsInMap;
    }

    @Override
    public void saveToFile() {
        try {

            String json = new ObjectMapper().writeValueAsString(receiptsInMap);
            filesServices.saveToFile(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private void readFromFile() {
        String json = filesServices.readFromFile();
        try {
            receiptsInMap =  new ObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES).readValue(json, new TypeReference<LinkedHashMap<Integer, ConnectedRecipe>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReceiptServicessimpl that = (ReceiptServicessimpl) o;
        return Objects.equals(filesServices, that.filesServices) && Objects.equals(receiptsInMap, that.receiptsInMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filesServices, receiptsInMap);
    }
}
