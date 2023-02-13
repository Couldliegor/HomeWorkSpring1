package com.egorreceipe.receiptapp.Controller;


import com.egorreceipe.receiptapp.Model.Ingridient;
import com.egorreceipe.receiptapp.Service.IngridServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/ingrid")
public class IngridientsController {
    private final IngridServices ingridServices;

    public IngridientsController(IngridServices ingridServices) {
        this.ingridServices = ingridServices;
    }

    @PostMapping("/add")
    public ResponseEntity<Ingridient> addIngrid(@RequestBody Ingridient ingridient) {
        Integer id = ingridServices.addIngridient(ingridient);
        return ResponseEntity.ok().body(ingridServices.getIngrid(id));
    }

    @GetMapping("/get")
    public ResponseEntity<Ingridient> getIngrid(@RequestParam int id) {
        if (ingridServices.getIngrid(id) == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ingridServices.getIngrid(id));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Ingridient> editIngridient(@PathVariable int id, @RequestBody Ingridient ingridient) {
        if (ingridServices.editIngridient(id, ingridient)) {
            return ResponseEntity.ok().body(ingridServices.getIngrid(id));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Ingridient> deleteIngridient(@PathVariable int id) {
        if (ingridServices.getIngrid(id) == null) {
            return ResponseEntity.notFound().build();
        }
            ingridServices.deleteIngridient(id);
            return ResponseEntity.ok().build();
    }

    @GetMapping("/get/all")
    public ResponseEntity<Map<Integer, Ingridient>> getAllIngridients() {
        if (ingridServices.getAllIngridients() == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(ingridServices.getAllIngridients());
    }
}

//?name=pineapple&countOfIngridients=20&typeOfUnit=pieces
//?name=apple&countOfIngridients=30&typeOfUnit=pieces
//?name=pasta&countOfIngridients=2&typeOfUnit=kilo
