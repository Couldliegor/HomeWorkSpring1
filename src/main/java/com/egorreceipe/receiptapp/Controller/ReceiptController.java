package com.egorreceipe.receiptapp.Controller;
import com.egorreceipe.receiptapp.Model.Recipe;
import com.egorreceipe.receiptapp.Service.ReceiptService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/recipe")
public class ReceiptController {
    private final ReceiptService receiptService;

    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @PostMapping("/add")
    public ResponseEntity<Recipe> addReceipe(@RequestBody Recipe recipe) {
        int id = receiptService.addRecipe(recipe);
        return ResponseEntity.ok().body(receiptService.getRecipe(id));
    }
    @GetMapping("/get")
    public String getReceipe(@RequestParam Integer id) {
        return receiptService.getRecipe(id).toString();
    }

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
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Recipe> deleteRecipe(@PathVariable int id){
        if (receiptService.deleteRecipe(id)) {
            return ResponseEntity.ok().body(receiptService.getRecipe(id));
        }
        return ResponseEntity.notFound().build();
    }

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
