package com.nicolasquintero.demo.model;

import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "recipes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Recipe {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID de la receta", example = "1")
    private Long id;
    
    @Column(nullable = false, unique = true)
    @Schema(description = "Nombre de la receta", example = "Yogurt natural")
    private String name;
    
    @Schema(description = "Descripción de la receta", example = "Receta básica para hacer yogurt natural en casa")
    private String description;
    
    @JsonIgnore
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    @Builder.Default
    @Schema(description = "Lista de ingredientes necesarios para la receta")
    private List<Ingredient> ingredients = new ArrayList<>();
    
    @Column(nullable = false)
    @Schema(description = "Volumen de leche recomendado para la receta", example = "1.5")
    private Double defaultMilkVolume; // litros
    
    @Column(nullable = false)
    @Schema(description = "Cantidad de cultivo iniciador recomendada para la receta", example = "2")
    private Double defaultStarterAmount; // cucharadas
    
    @Column(nullable = false)
    @Schema(description = "Temperatura de calentamiento recomendada para la receta", example = "85")
    private Double heatingTemperature; // °C
    
    @Column(nullable = false)
    @Schema(description = "Duración del calentamiento recomendada para la receta", example = "30")
    private Integer heatingDuration; // minutos a temperatura objetivo
    
    @Column(nullable = false)
    @Schema(description = "Temperatura de inoculación recomendada para la receta", example = "45")
    private Double inoculationTemperature; // °C
    
    @Column(nullable = false)
    @Schema(description = "Temperatura de incubación recomendada para la receta", example = "42")
    private Double incubationTemperature; // °C
    
    @Column(nullable = false)
    @Schema(description = "Tiempo mínimo de incubación recomendado para la receta", example = "6")
    private Integer minIncubationTime; // horas
    
    @Column(nullable = false)
    @Schema(description = "Tiempo máximo de incubación recomendado para la receta", example = "12")
    private Integer maxIncubationTime; // horas
    
    @Column(nullable = false)
    @Schema(description = "Tiempo de refrigeración recomendado para la receta", example = "4")
    private Integer refrigerationTime; // horas
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Schema(description = "Nivel de dificultad de la receta", example = "BEGINNER")
    private DifficultyLevel difficulty;
    
        @Schema(description = "Consejos adicionales para la receta", example = "Usar leche entera para un yogurt más cremoso")
    private String tips;
    
    @Column(nullable = false)
    @Schema(description = "Indica si la receta está activa", example = "true")
    private Boolean active;
    
    public enum DifficultyLevel {
        BEGINNER, INTERMEDIATE, ADVANCED
    }
}