package com.egorreceipe.receiptapp.Service;

public interface FilesRecipeService {
    boolean saveToFile(String json);

    String readFromFile();

    boolean cleanDataFile();
}
