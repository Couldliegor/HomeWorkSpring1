package com.egorreceipe.receiptapp.Service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface FilesIngridService {
    boolean saveToFile(String json);

    String readFromFile();

    boolean cleanDataFile();
    File getDataFile();

    boolean tryCheckConstruction(MultipartFile file);
}
