package com.nicolasquintero.demo.dto;

import java.util.List;

import com.nicolasquintero.demo.model.Recipe;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDto {
    @Schema(description = "ID de la receta", example = "1")
    private String name;

    @Schema(description = "Descripción de la receta", example = "Receta clásica de yogurt natural con un sabor suave y cremoso")
    private String description;

    @Schema(description = "Volumen de leche recomendado para la receta", example = "1.5")
    private Double defaultMilkVolume;

    @Schema(description = "Cantidad de cultivo iniciador recomendado para la receta", example = "2")
    private Double defaultStarterAmount;

    @Schema(description = "Temperatura de calentamiento recomendada para la receta", example = "85")
    private Double heatingTemperature;

    @Schema(description = "Duración del calentamiento recomendada para la receta", example = "30")
    private Integer heatingDuration;

    @Schema(description = "Temperatura de inoculación recomendada para la receta", example = "45")
    private Double inoculationTemperature;

    @Schema(description = "Temperatura de incubación recomendada para la receta", example = "42")
    private Double incubationTemperature;

    @Schema(description = "Tiempo mínimo de incubación recomendado para la receta en minutos", example = "480")
    private Integer minIncubationTime;

    @Schema(description = "Tiempo máximo de incubación recomendado para la receta en minutos", example = "720")
    private Integer maxIncubationTime;

    @Schema(description = "Tiempo de refrigeración recomendado para la receta en minutos", example = "60")
    private Integer refrigerationTime;

    @Schema(description = "Nivel de dificultad de la receta", example = "MEDIO")
    private Recipe.DifficultyLevel difficulty;

    @Schema(description = "Consejos para la receta", example = "Asegúrate de mantener una temperatura constante durante todo el proceso")
    private String tips;

    @Schema(description = "Lista de ingredientes para la receta")
    private List<IngredientDto> ingredients;
}
