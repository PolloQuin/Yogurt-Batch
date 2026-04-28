package com.nicolasquintero.demo.model;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "temperature_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class TemperatureLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID del registro de temperatura", example = "1")
    private Long id;

    @ManyToOne 
    @JoinColumn(name = "batch_id", nullable = false)
    @Schema(description = "Lote de yogurt al que pertenece el registro de temperatura")
    private YogurtBatch batch;

    @Column(nullable = false)
    @Schema(description = "Temperatura registrada", example = "45.0")
    private Double temperature;

    @Column(nullable = false)
    @Schema(description = "Fecha y hora en que se registró la temperatura", example = "2024-06-01T14:30:00")
    private LocalDateTime recordedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Schema(description = "Tipo de registro de temperatura", example = "HEATING")
    private LogType type;

    @Schema(description = "Notas adicionales sobre el registro de temperatura", example = "Temperatura alcanzada durante el proceso de calentamiento")
    private String notes;

    public enum LogType{
        HEATING,
        COOLING,
        INCUBATION,
        REFRIGERATION,
        MANUAL
    }

    
}
