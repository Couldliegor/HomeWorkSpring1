package com.egorreceipe.receiptapp.Service;

import java.io.File;

public interface FilesRecipeService {
    boolean saveToFile(String json);

    String readFromFile();

    boolean cleanDataFile();
    File getDataFile();
}
