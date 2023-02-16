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
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "name:Лазанья, posCountCookingInMin: 15"
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
                    responseCode = "400",
                    description = "Рецепт не был добавлен, проверьте ввод данных.",
                    content = {
                            @Content(
                                    mediaType = "application/json"
                            )
                    }
            )
    }
    )
    @PostMapping("/")
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
                    description = "Рецепт успешно получен!",
                    content = {
                            @Content(
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Такого рецепта не существует.",
                    content = {
                            @Content(
                                    mediaType = "application/json"
                            )
                    }
            ),
    }
    )
    @GetMapping("/")
    public Recipe getReceipe(@RequestParam Integer id) {
        return receiptService.getRecipe(id);
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
                    responseCode = "204",
                    description = "Рецепт не был отредактирован, проверьте ввод данных.",
                    content = {
                            @Content(
                                    mediaType = "application/json"
                            )
                    }
            )
    }
    )
    @PutMapping("/{id}")
    public ResponseEntity<Recipe> editRecipe(@PathVariable int id,@RequestBody Recipe recipe) {
        boolean recipe1 = receiptService.editRecipe(id, recipe);
        if (recipe1) {
            return ResponseEntity.ok().body(receiptService.getRecipe(id));
        }
            return ResponseEntity.noContent().build();
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
    @DeleteMapping("/{id}")
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
            )
    }
    )
    @GetMapping("/get")
    public ResponseEntity<Map<Integer, Recipe>> getAllRecipes() {
        return ResponseEntity.ok(receiptService.getAllRecipes());
    }
}

//add?name=lasagna&posCountCookingInMin=20
//add?name=lasagnas&posCountCookingInMin=35
