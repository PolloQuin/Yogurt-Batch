package com.nicolasquintero.demo.model;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity 
@Table(name = "yogurt_batches")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//Inventario de yogures
public class YogurtBatch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID del lote de yogurt", example = "1")
    private long id;//se necesita una id para cada yogurt y tmb un codigo, se coloca el tipo de dato(long,string) y el atributo
    
    @Column(nullable = false)
    @Schema(description = "Código del lote de yogurt", example = "YB 123456789")
    private String batchCode;

    @ManyToOne
    @JoinColumn(name = "recipe_id",nullable = false)
    private Recipe recipe;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Schema(description = "Estado del lote de yogurt", example = "PREPARING")
    private BatchStatus status;

    @Column(nullable = false)
    @Schema(description = "Volumen de leche del lote de yogurt", example = "1000.0")
    private Double milkVolume;

    @Column(nullable = false)
    @Schema(description = "Cantidad de cultivo iniciador del lote de yogurt", example = "2.0")
    private Double starterAmount;

    @Column(nullable = false)
    @Schema(description = "Temperatura objetivo para el lote de yogurt", example = "42.0")
    private Double targetTemperature;

    @Column(nullable = false)
    @Schema(description = "Duración de incubación del lote de yogurt en minutos", example = "480")
    private Integer incubationTime;
    
    @Schema(description = "Fecha y hora de inicio del proceso del lote de yogurt", example = "2024-06-01T14:00:00")
    private LocalDateTime starTime;

    @Schema(description = "Fecha y hora de finalización del proceso del lote de yogurt", example = "2024-06-02T22:00:00")
    private LocalDateTime incubarionStarTime;

    @Schema(description = "Fecha y hora de finalización del proceso de incubación del lote de yogurt", example = "2024-06-02T22:00:00")
    private LocalDateTime incubationEndTime;
    
    @Schema(description = "Fecha y hora de inicio del proceso de refrigeración del lote de yogurt", example = "2024-06-02T22:30:00")
    private LocalDateTime refrigerationStarTime;
 
    @OneToMany(mappedBy = "batch", cascade = CascadeType.ALL)
    @Builder.Default
    @Schema(description = "Lista de registros de temperatura asociados al lote de yogurt")
    private List<TemperatureLog> temperatureLogs = new ArrayList<>();

    @Schema(description = "Notas adicionales sobre el lote de yogurt", example = "Este lote es para una promoción especial")
    private String notes;

    @Column(nullable = false)
    @Schema(description = "Indica si el lote de yogurt es un lote de prueba", example = "false")
    private LocalDateTime createdAt;

    @Schema(description = "Fecha y hora de última actualización del lote de yogurt", example = "2024-06-01T15:00:00")
    private LocalDateTime updateAt;

    @PrePersist
    protected void onCreate(){
        createdAt = LocalDateTime.now();
        batchCode = "YB " + System.currentTimeMillis();
    }

    @PreUpdate
    protected void onUpdate(){
        updateAt = LocalDateTime.now();
    }
    
    public enum BatchStatus {
        PREPARING,
        HEATING,
        COOLING,
        INOCULATING,
        INCUBATION,
        REFRIGERATION,
        COMPLETED,
        FAILED, REFRIGERATING, INCUBATING
    }
}
//private boolean caminar(True){
//    return System.err.println("Este es el mensaje retorna"); }