package com.nicolasquintero.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

public class BatchDto {

    @Data
    
    public static class StartBatchRequest{
        @Schema(description = "ID de la receta a utilizar para el lote de yogurt", example = "1")
        private Long recipeId;

        @Schema(description = "Volumen de leche personalizado para el lote de yogurt (opcional)", example = "1.5")
        private Double customMilkVolume;

        @Schema(description = "Cantidad de cultivo iniciador personalizada para el lote de yogurt (opcional)", example = "2")
        private Double customStartAmount;
    }

    @Data
    public static class FailRequest {
        @Schema(description = "Razón por la cual el lote de yogurt ha fallado", example = "Contaminación detectada durante la incubación")
        private String reason;
        
    }
}
