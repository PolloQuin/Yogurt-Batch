package com.nicolasquintero.demo.dto;

import com.nicolasquintero.demo.model.TemperatureLog;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TemperatureRecordDto {
    @Schema(description = "ID del lote de yogurt al que pertenece el registro de temperatura", example = "1")
    private Double temperaure;

    @Schema(description = "Fecha y hora en que se registró la temperatura", example = "2024-06-01T14:30:00")
    private TemperatureLog.LogType type;
}
