package com.egorreceipe.receiptapp.Controller;

import com.egorreceipe.receiptapp.Service.FilesRecipeService;
import com.egorreceipe.receiptapp.Service.impl.FilesIngridServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@RequestMapping("/file")
public class FileController {
    private final FilesRecipeService filesRecipeService;

    private final FilesRecipeService filesIngridService;

    public FileController(@Qualifier("filesRecipeServiceImpl") FilesRecipeService filesRecipeService, @Qualifier("filesIngridServiceImpl") FilesIngridServiceImpl filesIngridService) {
        this.filesRecipeService = filesRecipeService;
        this.filesIngridService = filesIngridService;
    }

    @Operation(
            summary = "Получение файла с сервера в формате .json"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Файл успешно получен!",
                    content = {
                            @Content(
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "ОШИБКА, файл где-то потерялся на сервере!",
                    content = {
                            @Content(
                                    mediaType = "application/json"
                            )
                    }
            )
    }
    )
    @GetMapping(value = "/export/Recipe", produces = MediaType.APPLICATION_JSON_VALUE) // тип значения produces
    public ResponseEntity<InputStreamResource> downloadDataFile() throws FileNotFoundException {
        File file = filesRecipeService.getDataFile();
        if (file.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));//так создается поток данных
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON) // передача данных по байтово // МОЖНО БРАТЬ И ГРУЗИТЬ!!!
                    .contentLength(file.length())//длинна
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"RecipeList.json\"")// тип
                    .body(resource); //для чего указали длинну-хз))
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @Operation(
            summary = "Загрузка файла рецептов в формате .json"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Файл успешно загружен на сервер!",
                    content = {
                            @Content(
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "ОШИБКА, возможно, проблема в типе данных или еще в чем то !",
                    content = {
                            @Content(
                                    mediaType = "application/json"
                            )
                    }
            )
    }
    )
    @PostMapping(value = "/import/Recipe", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    //загрузка файла в систему // consumes = то, что мы принимаем
    public ResponseEntity<Void> uploadRecipeDataFile(@RequestParam MultipartFile file) { //класс MultiFile дает всю необходимую информацию о файле, что крайне удобно
        boolean b = filesRecipeService.tryCheckConstruction(file);
        if (b) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @Operation(
            summary = "Загрузка файла ингридиентов в формате .json"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Файл успешно загружен на сервер!",
                    content = {
                            @Content(
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "ОШИБКА, возможно, проблема в типе данных или еще в чем то !",
                    content = {
                            @Content(
                                    mediaType = "application/json"
                            )
                    }
            )
    }
    )
    @PostMapping(value = "/import/Ingrid", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    //загрузка файла в систему // consumes = то, что мы принимаем
    public ResponseEntity<Void> uploadIngridDataFile(@RequestParam MultipartFile file) { //класс MultiFile дает всю необходимую информацию о файле, что крайне удобно
        boolean b = filesIngridService.tryCheckConstruction(file);
        if (b) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
