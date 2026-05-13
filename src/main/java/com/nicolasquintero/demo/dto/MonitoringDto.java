package com.nicolasquintero.demo.dto;

import java.util.Map;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

public class MonitoringDto {
    
    @Data
    @Builder
    public static class TemperatureSummary {
        @Schema(description = "Temperatura actual del lote de yogurt", example = "42.5")
        private Double currentTemperature;

        @Schema(description = "Temperatura máxima registrada durante la incubación", example = "43.0")
        private Double maximumTemperature;

        @Schema(description = "Temperatura mínima registrada durante la incubación", example = "41.5")
        private Double minimumTemperature;

        @Schema(description = "Temperatura promedio registrada durante la incubación", example = "42.0")
        private Double averageTemperature;
        
    }

    @Data
    @Builder
    public static class Dashboard {
        @Schema(description = "Conteo de lotes de yogurt por estado (activo, completado, fallido)", example = "{\"ACTIVE\": 3, \"COMPLETED\": 5, \"FAILED\": 1}")
        private Map<String, Long> batchCounts;

        @Schema(description = "Número de lotes de yogurt actualmente activos", example = "3")
        private Long activeBatchCount;

        @Schema(description = "Número de lotes de yogurt completados hoy", example = "2")
        private Integer completedToday;
        
    }

}
