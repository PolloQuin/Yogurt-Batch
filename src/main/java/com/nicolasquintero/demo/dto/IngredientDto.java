package com.nicolasquintero.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IngredientDto {
    @Schema(description = "Nombre del ingrediente", example = "Azúcar")
    private String name;

    @Schema(description = "Cantidad del ingrediente", example = "100")
    private Double quantity;

    @Schema(description = "Unidad de medida del ingrediente", example = "g")
    private String unit;

    @Schema(description = "Notas adicionales sobre el ingrediente", example = "Usar azúcar moreno para un sabor más intenso")
    private String notes;

    @Schema(description = "Indica si el ingrediente es opcional", example = "false")
    private Boolean optional;
}