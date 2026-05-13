package com.nicolasquintero.demo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ingredients")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ingredient {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID del ingrediente", example = "1")
    private Long id;
    
    @Column(nullable = false)
    @Schema(description = "Nombre del ingrediente", example = "Azúcar")
    private String name;
    
    @Schema(description = "Cantidad del ingrediente", example = "100")
    private Double quantity;
    
    @Schema(description = "Unidad de medida del ingrediente", example = "g")
    private String unit; // kg, g, ml, cucharadas, etc.
    
    @ManyToOne
    @JoinColumn(name = "recipe_id", nullable = false)
    @Schema(description = "Receta a la que pertenece el ingrediente")
    private Recipe recipe;
    
    @Schema(description = "Notas adicionales sobre el ingrediente", example = "Usar azúcar moreno para un sabor más intenso")
    private String notes;
    
    @Column(nullable = false)
    @Schema(description = "Indica si el ingrediente es opcional", example = "false")
    private Boolean optional;
}