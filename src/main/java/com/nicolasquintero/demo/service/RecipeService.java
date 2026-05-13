package com.nicolasquintero.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nicolasquintero.demo.domain.repository.RecipeRepository;
import com.nicolasquintero.demo.dto.RecipeDto;

import com.nicolasquintero.demo.exception.BusinessException;
import com.nicolasquintero.demo.model.Ingredient;
import com.nicolasquintero.demo.model.Recipe;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecipeService {
    
    private final RecipeRepository recipeRepository;
    
    @Transactional
    public Recipe createRecipe(RecipeDto recipeDTO) {
        if (recipeRepository.findByName(recipeDTO.getName()).isPresent()) {
            throw new BusinessException("Recipe with name '" + recipeDTO.getName() + "' already exists");
        }
        
        Recipe recipe = Recipe.builder()
            .name(recipeDTO.getName())
            .description(recipeDTO.getDescription())
            .defaultMilkVolume(recipeDTO.getDefaultMilkVolume())
            .defaultStarterAmount(recipeDTO.getDefaultStarterAmount())
            .heatingTemperature(recipeDTO.getHeatingTemperature())
            .heatingDuration(recipeDTO.getHeatingDuration())
            .inoculationTemperature(recipeDTO.getInoculationTemperature())
            .incubationTemperature(recipeDTO.getIncubationTemperature())
            .minIncubationTime(recipeDTO.getMinIncubationTime())
            .maxIncubationTime(recipeDTO.getMaxIncubationTime())
            .refrigerationTime(recipeDTO.getRefrigerationTime())
            .difficulty(recipeDTO.getDifficulty())
            .tips(recipeDTO.getTips())
            .active(true)
            .build();
        
        // Agregar ingredientes
        if (recipeDTO.getIngredients() != null) {
            recipeDTO.getIngredients().forEach(ingredientDto -> {
                Ingredient ingredient = Ingredient.builder()
                    .name(ingredientDto.getName())
                    .quantity(ingredientDto.getQuantity())
                    .unit(ingredientDto.getUnit())
                    .notes(ingredientDto.getNotes())
                    .optional(ingredientDto.getOptional())
                    .recipe(recipe)
                    .build();
                recipe.getIngredients().add(ingredient);
            });
        }
        
        Recipe savedRecipe = recipeRepository.save(recipe);
        log.info("Recipe created: {}", savedRecipe.getName());
        
        return savedRecipe;
    }
    
    @Transactional
    public Recipe updateRecipe(Long id, RecipeDto recipeDto) {
        Recipe recipe = getRecipe(id);
        
        recipe.setName(recipeDto.getName());
        recipe.setDescription(recipeDto.getDescription());
        recipe.setDefaultMilkVolume(recipeDto.getDefaultMilkVolume());
        recipe.setDefaultStarterAmount(recipeDto.getDefaultStarterAmount());
        recipe.setHeatingTemperature(recipeDto.getHeatingTemperature());
        recipe.setHeatingDuration(recipeDto.getHeatingDuration());
        recipe.setInoculationTemperature(recipeDto.getInoculationTemperature());
        recipe.setIncubationTemperature(recipeDto.getIncubationTemperature());
        recipe.setMinIncubationTime(recipeDto.getMinIncubationTime());
        recipe.setMaxIncubationTime(recipeDto.getMaxIncubationTime());
        recipe.setRefrigerationTime(recipeDto.getRefrigerationTime());
        recipe.setDifficulty(recipeDto.getDifficulty());
        recipe.setTips(recipeDto.getTips());
        
        // Actualizar ingredientes (simplificado - en producción se manejaría mejor)
        recipe.getIngredients().clear();
        if (recipeDto.getIngredients() != null) {
            recipeDto.getIngredients().forEach(ingredientDto -> {
                Ingredient ingredient = Ingredient.builder()
                    .name(ingredientDto.getName())
                    .quantity(ingredientDto.getQuantity())
                    .unit(ingredientDto.getUnit())
                    .notes(ingredientDto.getNotes())
                    .optional(ingredientDto.getOptional())
                    .recipe(recipe)
                    .build();
                recipe.getIngredients().add(ingredient);
            });
        }
        
        Recipe updatedRecipe = recipeRepository.save(recipe);
        log.info("Recipe updated: {}", updatedRecipe.getName());
        
        return updatedRecipe;
    }
    
    public Recipe getRecipe(Long id) {
        return recipeRepository.findById(id)
            .orElseThrow(() -> new BusinessException("Recipe not found with id: " + id));
    }
    
    public List<Recipe> getAllActiveRecipes() {
        return recipeRepository.findByActive(true);
    }
    
    public List<Recipe> searchRecipes(String keyword) {
        return recipeRepository.searchByKeyword(keyword);
    }
    
    @Transactional
    public void deactivateRecipe(Long id) {
        Recipe recipe = getRecipe(id);
        recipe.setActive(false);
        recipeRepository.save(recipe);
        log.info("Recipe deactivated: {}", recipe.getName());
    }
    
    @Transactional
    public void activateRecipe(Long id) {
        Recipe recipe = getRecipe(id);
        recipe.setActive(true);
        recipeRepository.save(recipe);
        log.info("Recipe activated: {}", recipe.getName());
    }
}