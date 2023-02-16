package com.egorreceipe.receiptapp.Service.impl;

import com.egorreceipe.receiptapp.Model.Ingridient;
import com.egorreceipe.receiptapp.Service.IngridServices;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.LinkedHashMap;
import java.util.Map;


@Service
public class IngridServicesImpl implements IngridServices {
    public LinkedHashMap<Integer, Ingridient> ingridInMap = new LinkedHashMap<>();
    public static int id = 0;
    public IngridServicesImpl(FilesIngridServiceImpl filesServices) {
        this.filesServices = filesServices;
    }

    private final FilesIngridServiceImpl filesServices;
    @PostConstruct
    private void init() {
        readFromFile();
    }
    @Override
    public Integer addIngridient(Ingridient ingridient) {
        checkingForIlligalArduments(ingridient);
        ingridInMap.put(id, ingridient);
        saveToFile();
        return id++;
    }
    @Override
    public Ingridient getIngrid(Integer id) {
        return ingridInMap.get(id);
    }
    @Override //String utils
    public void checkingForIlligalArduments(Ingridient ingridient) {
        if (ingridient.getCountOfIngridients() < 0) {
            throw new IllegalArgumentException("Неправильно введено количество ингридиентов");
        }
        if (StringUtils.isBlank(ingridient.getName()) || StringUtils.isEmpty(ingridient.getName())) {
            throw new IllegalArgumentException("Имя ингридиента не может быть пустым");
        }
        if (StringUtils.isBlank(ingridient.getTypeOfUnit()) || StringUtils.isEmpty(ingridient.getTypeOfUnit())) {
            throw new IllegalArgumentException("Единица измерения не может быть пустой");
        }
    }

    @Override
    public boolean editIngridient(int id, Ingridient ingridient) {
        if (ingridInMap.get(id) != null) {
            ingridInMap.put(id, ingridient);
            saveToFile();
            return true;
        }
            return false;
    }

    @Override
    public void deleteIngridient(int id) {
            ingridInMap.remove(id);
    }

    @Override
    public Map<Integer, Ingridient> getAllIngridients() {
        return ingridInMap;
    }
    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(ingridInMap);
            filesServices.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFromFile() {
        String json = filesServices.readFromFile();
        try {
            ingridInMap =  new ObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES).readValue(json, new TypeReference<LinkedHashMap<Integer, Ingridient>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
