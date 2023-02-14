package com.egorreceipe.receiptapp.Controller;
import com.egorreceipe.receiptapp.Model.Recipe;
import com.egorreceipe.receiptapp.Service.ReceiptService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/recipe")
@Tag(name = "Рецепты", description = "CRUD Операции с Рецептами")
public class ReceiptController {
    private final ReceiptService receiptService;

    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }
    @Operation(
            summary = "Добавление/создание рецепта",
            description = "рецепт создается по параметрам"
    )
    @Parameter(
            name = "name", example  = "Лазанья"
    )
    @Parameter(
            name = "posCountCookingInMin", example  = "15"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рнецепт успешно добавлен!",
                    content = {
                            @Content(
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Рецепт не было добавлен.",
                    content = {
                            @Content(
                                    mediaType = "application/json"
                            )
                    }
            )
    }
    )
    @PostMapping("/add")
    public ResponseEntity<Recipe> addReceipe(@RequestBody Recipe recipe) {
        int id = receiptService.addRecipe(recipe);
        return ResponseEntity.ok().body(receiptService.getRecipe(id));
    }
    @Operation(
            summary = "Получение рецепта",
            description = "Получение рецепта по id"
    )
    @Parameter(
            name = "id", example  = "0"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рнецепт успешно получен!",
                    content = {
                            @Content(
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Рецепт не был получен.",
                    content = {
                            @Content(
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Такого рецепта не существует.",
                    content = {
                            @Content(
                                    mediaType = "application/json"
                            )
                    }
            )
    }
    )
    @GetMapping("/get")
    public String getReceipe(@RequestParam Integer id) {
        return receiptService.getRecipe(id).toString();
    }

    @Operation(
            summary = "Редактирование рецепта по айди, json",
            description = "Получение рецепта по id, json"
    )
    @Parameter(
            name = "id", example  = "0"
    )
    @Parameter(
            name = "json", example  = "пример можете видеть ниже при использовании SWAGGER UI"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рнецепт успешно отредактирован!",
                    content = {
                            @Content(
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Рецепт не был отредактирован, проверьте ввод данных.",
                    content = {
                            @Content(
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Такого рецепта не существует.",
                    content = {
                            @Content(
                                    mediaType = "application/json"
                            )
                    }
            )
    }
    )
    @PutMapping("/edit/{id}")
    public ResponseEntity<Recipe> editRecipe(@PathVariable int id,@RequestBody Recipe recipe) {
        if (receiptService.editRecipe(id, recipe)) {
            return ResponseEntity.ok().body(receiptService.getRecipe(id));
        } else if (!receiptService.editRecipe(id, recipe)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }
    @Operation(
            summary = "Удаление рецепта",
            description = "Удаление по айди"
    )
    @Parameter(
            name = "id", example  = "0"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепт удален!",
                    content = {
                            @Content(
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Рецепт не был удален, проверьте ввод данных.",
                    content = {
                            @Content(
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Такого рецепта не существует.",
                    content = {
                            @Content(
                                    mediaType = "application/json"
                            )
                    }
            )
    }
    )
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Recipe> deleteRecipe(@PathVariable int id){
        if (receiptService.deleteRecipe(id)) {
            return ResponseEntity.ok().body(receiptService.getRecipe(id));
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(
            summary = "получение всех добавленных рецептов"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рнецепты успешно получены!",
                    content = {
                            @Content(
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Рецепты не были получены, добавьте рецепты.",
                    content = {
                            @Content(
                                    mediaType = "application/json"
                            )
                    }
            )
    }
    )
    @GetMapping("/get/all")
    public ResponseEntity<Map<Integer, Recipe>> getAllRecipes() {
        if (receiptService.getAllRecipes() == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(receiptService.getAllRecipes());
    }
}

//add?name=lasagna&posCountCookingInMin=20
//add?name=lasagnas&posCountCookingInMin=35
