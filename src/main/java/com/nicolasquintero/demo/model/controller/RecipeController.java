package com.nicolasquintero.demo.model.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nicolasquintero.demo.dto.RecipeDto;
import com.nicolasquintero.demo.model.Recipe;
import com.nicolasquintero.demo.service.RecipeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/recipes")
@RequiredArgsConstructor
@Tag(name = "Recetas", description = "Operaciones relacionadas con recetas de yogurt")
public class RecipeController {
    private final RecipeService recipeService;
    

    @Operation(summary = "Crear receta")
    @ApiResponses({
    @ApiResponse(responseCode = "201", description = "Receta creada correctamente"),
    @ApiResponse(responseCode = "400", description = "Datos de la receta inválidos")
    })

    @PostMapping
    public ResponseEntity<Recipe> createRecipe(@RequestBody RecipeDto recipeDto) {
        Recipe recipe = recipeService.createRecipe(recipeDto);
        return new ResponseEntity<>(recipe, HttpStatus.CREATED);
    }
    
    @Operation(summary = "Actualizar receta")
    @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Receta actualizada correctamente"),
    @ApiResponse(responseCode = "404", description = "No se encontró la receta con el ID proporcionado")
    })  

    @PutMapping("/{id}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable Long id, @RequestBody RecipeDto recipeDto) {
        Recipe recipe = recipeService.updateRecipe(id, recipeDto);
        return ResponseEntity.ok(recipe);
    }
    
     @Operation(
        summary = "Obtener receta por ID",
        description = "Devuelve una receta específica a partir de su identificador único"
    )

    @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Receta encontrada correctamente"),
    @ApiResponse(responseCode = "404", description = "No se encontró la receta con el ID proporcionado")
    })

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable Long id) {
        Recipe recipe = recipeService.getRecipe(id);
        return ResponseEntity.ok(recipe);
    }
    
    @Operation(summary = "Obtener todas las recetas")
    @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Recetas encontradas correctamente"),
    })

    @GetMapping
    public ResponseEntity<List<Recipe>> getAllRecipes() {
        return ResponseEntity.ok(recipeService.getAllActiveRecipes());
    }
    
    @Operation(summary = "Buscar recetas")
    @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Recetas encontradas correctamente"),
    })

    @GetMapping("/search")
    public ResponseEntity<List<Recipe>> searchRecipes(@RequestParam String keyword) {
        return ResponseEntity.ok(recipeService.searchRecipes(keyword));
    }
    
    @Operation(summary = "Desactivar receta")
    @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Receta desactivada correctamente"),
    @ApiResponse(responseCode = "404", description = "No se encontró la receta con el ID proporcionado")
    })

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateRecipe(@PathVariable Long id) {
        recipeService.deactivateRecipe(id);
        return ResponseEntity.ok().build();
    }
    
    @Operation(summary = "Activar receta")
    @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Receta activada correctamente"),
    @ApiResponse(responseCode = "404", description = "No se encontró la receta con el ID proporcionado")
    })

    @PatchMapping("/{id}/activate")
    public ResponseEntity<Void> activateRecipe(@PathVariable Long id) {
        recipeService.activateRecipe(id);
        return ResponseEntity.ok().build();
    }
}
