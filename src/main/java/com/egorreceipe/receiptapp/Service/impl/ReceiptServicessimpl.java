package com.egorreceipe.receiptapp.Service.impl;

import com.egorreceipe.receiptapp.Model.Recipe;
import com.egorreceipe.receiptapp.Service.ReceiptService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
@Service
public class ReceiptServicessimpl implements ReceiptService {
    private final FilesRecipeServiceImpl filesServices;
    public LinkedHashMap<Integer, Recipe> receiptsInMap = new LinkedHashMap<>();
    public static Integer id = 0;

    public ReceiptServicessimpl(FilesRecipeServiceImpl filesServices) {
        this.filesServices = filesServices;
    }

    @PostConstruct // проблема тут
    private void init() {
        readFromFile();
    }
    @Override
    public Integer addRecipe(Recipe recipe) {
        checkingForIllegalArguments(recipe);
        receiptsInMap.put(id, recipe);
        saveToFile();
        return id++;
    }

    @Override
    public Recipe getRecipe(Integer id2) {
        return receiptsInMap.get(id2);
    }
    @Override
    public void checkingForIllegalArguments(Recipe recipe) {
        if (recipe.getPosCountCookingInMin() < 0) {
            throw new IllegalArgumentException("Неправильно введено количество ингридиентов");
        }
        if (StringUtils.isEmpty(recipe.getName()) || StringUtils.isBlank(recipe.getName())) {
            throw new IllegalArgumentException("Имя ингридиента не может быть пустым");
        }
    }

    @Override
    public boolean editRecipe(int id, Recipe recipe) {
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
            return true;
        }
        return false;
    }
    @Override
    public Map<Integer, Recipe> getAllRecipes() {
        return receiptsInMap;
    }
    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(receiptsInMap);
            filesServices.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFromFile() {
        String json = filesServices.readFromFile();
        try {
            receiptsInMap =  new ObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES).readValue(json, new TypeReference<LinkedHashMap<Integer, Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
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
