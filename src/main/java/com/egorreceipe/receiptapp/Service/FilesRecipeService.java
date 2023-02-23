package com.egorreceipe.receiptapp.Service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;

public interface FilesRecipeService {
    Path createTempRecipeFile(String suffix);

    boolean saveToFile(String json);

    String readFromFile();

    boolean cleanDataFile();
    File getDataFile();

    boolean tryCheckConstruction(MultipartFile file);
}
