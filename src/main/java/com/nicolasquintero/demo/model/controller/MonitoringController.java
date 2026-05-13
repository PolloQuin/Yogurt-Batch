package com.nicolasquintero.demo.model.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nicolasquintero.demo.domain.repository.TemperatureLogRepository;
import com.nicolasquintero.demo.domain.repository.YogurtBatchRepository;
import com.nicolasquintero.demo.dto.MonitoringDto;
import com.nicolasquintero.demo.dto.MonitoringDto.TemperatureSummary.TemperatureSummaryBuilder;
import com.nicolasquintero.demo.model.TemperatureLog;
import com.nicolasquintero.demo.model.YogurtBatch;
import com.nicolasquintero.demo.service.TemperatureControlService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/monitoring")
@RequiredArgsConstructor
public class MonitoringController {
    private final YogurtBatchRepository batchRepository = null;
    private final TemperatureLogRepository temperatureLogRepository = null;
    private final TemperatureControlService temperatureControlService = new TemperatureControlService();
    
    @Operation(summary = "Obtener lotes de yogurt activos")
    @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Lotes encontrados correctamente"),
    @ApiResponse(responseCode = "404", description = "No se encontraron lotes activos")
    })

    @GetMapping("/batches/active")
    public ResponseEntity<List<YogurtBatch>> getActiveBatches() {
        List<YogurtBatch> activeBatches = batchRepository.findByStatus(YogurtBatch.BatchStatus.INCUBATING);
        activeBatches.addAll(batchRepository.findByStatus(YogurtBatch.BatchStatus.HEATING));
        activeBatches.addAll(batchRepository.findByStatus(YogurtBatch.BatchStatus.COOLING));
        activeBatches.addAll(batchRepository.findByStatus(YogurtBatch.BatchStatus.REFRIGERATING));
        return ResponseEntity.ok(activeBatches);
    }
    
    @Operation(summary = "Obtener resumen de temperatura para un lote de yogurt")
    @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Resumen de temperatura obtenido correctamente"),
    @ApiResponse(responseCode = "404", description = "No se encontró el lote de yogurt con el ID proporcionado")
    })

    @GetMapping("/batches/{batchId}/temperature")
    public ResponseEntity<MonitoringDto.TemperatureSummary> getBatchTemperatureSummary(@PathVariable Long batchId) {
        Double currentTemp = temperatureControlService.getCurrentTemperature(batchId);
        Double maxTemp = temperatureLogRepository.getMaxTemperatureByBatch(batchId);
        Double minTemp = temperatureLogRepository.getMinTemperatureByBatch(batchId);
        Double avgTemp = temperatureLogRepository.getAverageTemperatureByBatchAndType(
            batchId, TemperatureLog.LogType.INCUBATION);
        
        MonitoringDto.TemperatureSummary summary = ((TemperatureSummaryBuilder) MonitoringDto.TemperatureSummary.builder())
            .currentTemperature(currentTemp)
            .maximumTemperature(maxTemp)
            .minimumTemperature(minTemp)
            .averageTemperature(avgTemp)
            .build();
        
        return ResponseEntity.ok(summary);
    }
    
    @Operation(summary = "Obtener registros de temperatura para un lote de yogurt")
    @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Registros de temperatura obtenidos correctamente"),
    @ApiResponse(responseCode = "404", description = "No se encontró el lote de yogurt con el ID proporcionado")
    })

    @GetMapping("/batches/{batchId}/temperature-logs")
    public ResponseEntity<List<TemperatureLog>> getTemperatureLogs(
            @PathVariable Long batchId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        
        if (start != null && end != null) {
            return ResponseEntity.ok(temperatureLogRepository.findByBatchAndTimeRange(batchId, start, end));
        }
        
        YogurtBatch batch = batchRepository.findById(batchId).orElseThrow();
        return ResponseEntity.ok(temperatureLogRepository.findByBatch(batch));
    }
    
    @Operation(summary = "Obtener panel de control de monitoreo")
    @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Panel de control obtenido correctamente"),
    @ApiResponse(responseCode = "404", description = "No se encontraron lotes activos")
    })

    @GetMapping("/dashboard")
    public ResponseEntity<MonitoringDto.Dashboard> getDashboard() {
        long preparingCount = batchRepository.countByStatus(YogurtBatch.BatchStatus.PREPARING);
        long heatingCount = batchRepository.countByStatus(YogurtBatch.BatchStatus.HEATING);
        long coolingCount = batchRepository.countByStatus(YogurtBatch.BatchStatus.COOLING);
        long incubatingCount = batchRepository.countByStatus(YogurtBatch.BatchStatus.INCUBATING);
        long refrigeratingCount = batchRepository.countByStatus(YogurtBatch.BatchStatus.REFRIGERATING);
        long completedCount = batchRepository.countByStatus(YogurtBatch.BatchStatus.COMPLETED);
        long failedCount = batchRepository.countByStatus(YogurtBatch.BatchStatus.FAILED);
        
        Map<String, Long> batchCounts = new HashMap<>();
        batchCounts.put("PREPARING", preparingCount);
        batchCounts.put("HEATING", heatingCount);
        batchCounts.put("COOLING", coolingCount);
        batchCounts.put("INCUBATING", incubatingCount);
        batchCounts.put("REFRIGERATING", refrigeratingCount);
        batchCounts.put("COMPLETED", completedCount);
        batchCounts.put("FAILED", failedCount);
        
        MonitoringDto.Dashboard dashboard = MonitoringDto.Dashboard.builder()
            .batchCounts(batchCounts)
            .activeBatchCount(preparingCount + heatingCount + coolingCount + incubatingCount + refrigeratingCount)
            .completedToday(batchRepository.findByStatusAndDateRange(
                YogurtBatch.BatchStatus.COMPLETED, 
                LocalDateTime.now().withHour(0).withMinute(0), 
                LocalDateTime.now()).size())
            .build();
        
        return ResponseEntity.ok(dashboard);

    }
}