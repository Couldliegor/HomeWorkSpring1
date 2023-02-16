package com.egorreceipe.receiptapp.Controller;


import com.egorreceipe.receiptapp.Model.Ingridient;
import com.egorreceipe.receiptapp.Service.IngridServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/ingrid")
@Tag(name = "Ингридиенты", description = "CRUD Операции с ингридиентами")
public class IngridientsController {
    private final IngridServices ingridServices;

    public IngridientsController(IngridServices ingridServices) {
        this.ingridServices = ingridServices;
    }

    @Operation(
            summary = "Добавление ингридиента по заданным параметрам",
            description = "Параметры: name, countOfIngridient, typeOfUnit "
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description =  "name: Яблоки, countOfIngridient: 3, typeOfUnit: кг"

    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ингридиенты успешно добавлены!",
                    content = {
                            @Content(
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Ингридиент не был добавлен, проверьте ввод значений",
                    content = {
                            @Content(
                                    mediaType = "application/json"
                            )
                    }
            )
    }
    )
    @PostMapping("/")
    public ResponseEntity<Ingridient> addIngrid(@RequestBody Ingridient ingridient) {
        Integer id = ingridServices.addIngridient(ingridient);
        return ResponseEntity.ok().body(ingridServices.getIngrid(id));
    }

    @Operation(
            summary = "Получение ингридиента по айди",
            description = "Параметры: id"
    )
    @Parameter(
            name = "id", example  = "0"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ингридиент успешно получен!",
                    content = {
                            @Content(
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "Ингридиент не был найден.",
                    content = {
                            @Content(
                                    mediaType = "application/json"
                            )
                    }
            )
    }
    )
    @GetMapping("/")
    public ResponseEntity<Ingridient> getIngrid(@RequestParam int id) {
        if (ingridServices.getIngrid(id) == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ingridServices.getIngrid(id));
    }
    @Operation(
            summary = "Редактирование ингридиента ингридиента по айди, json",
            description = "Параметры: id, json"
    )
    @Parameters(value = {
            @Parameter(
                    name = "id", example  = "0"
            )
    })
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ингридиент успешно изменен!",
                    content = {
                            @Content(
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ингридиент не был найден.",
                    content = {
                            @Content(
                                    mediaType = "application/json"
                            )
                    }
            )
    }
    )
    @PutMapping("/{id}")
    public ResponseEntity<Ingridient> editIngridient(@PathVariable int id, @RequestBody Ingridient ingridient) {
        if (ingridServices.editIngridient(id, ingridient)) {
            return ResponseEntity.ok().body(ingridServices.getIngrid(id));
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(
            summary = "Удаление ингридиента по айди",
            description = "Параметры: id"
    )
    @Parameters(value = {
            @Parameter(
                    name = "id", example  = "0"
            )
    })
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ингридиент удален!",
                    content = {
                            @Content(
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ингридиент не был найден.",
                    content = {
                            @Content(
                                    mediaType = "application/json"
                            )
                    }
            )
    }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Ingridient> deleteIngridient(@PathVariable int id) {
        if (ingridServices.getIngrid(id) == null) {
            return ResponseEntity.notFound().build();
        }
        ingridServices.deleteIngridient(id);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Получение всех ингридиентов"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ингридиенты успешно получены!",
                    content = {
                            @Content(
                                    mediaType = "application/json"
                            )
                    }
            )
    }
    )
    @GetMapping("/get")
    public ResponseEntity<Map<Integer, Ingridient>> getAllIngridients() {
        Map<Integer, Ingridient> map = ingridServices.getAllIngridients() ;
        if (map == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(map);
    }
}

//?name=pineapple&countOfIngridients=20&typeOfUnit=pieces
//?name=apple&countOfIngridients=30&typeOfUnit=pieces
//?name=pasta&countOfIngridients=2&typeOfUnit=kilo
