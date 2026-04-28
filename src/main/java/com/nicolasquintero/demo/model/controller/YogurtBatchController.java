package com.nicolasquintero.demo.model.controller;

import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nicolasquintero.demo.dto.BatchDto;
import com.nicolasquintero.demo.model.YogurtBatch;
import com.nicolasquintero.demo.service.YogurtMakingService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/batches")
@RequiredArgsConstructor

public class YogurtBatchController {

    private final YogurtMakingService yogurtMakingService;

    @Operation(summary = "Iniciar un nuevo lote de yogurt")
    @ApiResponses({
    @ApiResponse(responseCode = "201", description = "Lote de yogurt creado correctamente"),
    @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })

    @PostMapping
    public ResponseEntity<YogurtBatch> startNewBatch(@RequestBody BatchDto.StartBatchRequest request){
        YogurtBatch batch = yogurtMakingService.startNewBatch(
            request.getRecipeId(),
            request.getCustomMilkVolume(),
            request.getCustomStartAmount()
        );
        return new ResponseEntity<>(batch, HttpStatus.CREATED);
    }

    @Operation(summary = "Iniciar el proceso de calentamiento para un lote de yogurt")
    @ApiResponses({
    @ApiResponse(responseCode = "201", description = "Proceso de calentamiento iniciado correctamente"),
    @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })

    @PostMapping("/{batchId}/heating")
    public ResponseEntity<YogurtBatch> startHeating(@PathVariable Long batchId){
        YogurtBatch batch = yogurtMakingService.startHeating(batchId);
        return ResponseEntity.ok(batch);
    }

    @Operation(summary = "Iniciar el proceso de inoculación para un lote de yogurt")
    @ApiResponses({
    @ApiResponse(responseCode = "201", description = "Proceso de inoculación iniciado correctamente"),
    @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })

    @PostMapping("/{batchId}/inoculating")
    public ResponseEntity<YogurtBatch> startInoculating(@PathVariable Long batchId){
        YogurtBatch batch = yogurtMakingService.startInoculating(batchId);
        return ResponseEntity.ok(batch);
    }

    @Operation(summary = "Iniciar el proceso de incubación para un lote de yogurt")
    @ApiResponses({
    @ApiResponse(responseCode = "201", description = "Proceso de incubación iniciado correctamente"),
    @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })

    @PostMapping("/{batchId}/incubation")
    public ResponseEntity<YogurtBatch> startIncubation(@PathVariable Long batchId){
        YogurtBatch batch = yogurtMakingService.startIncubation(batchId);
        return ResponseEntity.ok(batch);
    }

    @Operation(summary = "Iniciar el proceso de refrigeración para un lote de yogurt")
    @ApiResponses({
    @ApiResponse(responseCode = "201", description = "Proceso de refrigeración iniciado correctamente"),
    @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })

    @PostMapping("/{batchId}/refrigeration")
    public ResponseEntity<YogurtBatch> startRefrigeration(@PathVariable Long batchId){
        YogurtBatch batch = yogurtMakingService.startRefrigeration(batchId);
        return ResponseEntity.ok(batch);
    }

    @Operation(summary = "Completar un lote de yogurt")
    @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Lote de yogurt completado correctamente"),
    @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })

    @PostMapping("/{batchId}/complete")
    public ResponseEntity<YogurtBatch> completeBatch(@PathVariable Long batchId){
        YogurtBatch batch = yogurtMakingService.completeBatch(batchId);
        return ResponseEntity.ok(batch);
    }

    @Operation(summary = "Marcar un lote de yogurt como fallido")
    @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Lote de yogurt marcado como fallido correctamente"),
    @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })

    @PostMapping("/{batchId}/fail")
    public ResponseEntity<YogurtBatch> markAsFailed(
        @PathVariable Long batchId,
        @RequestBody BatchDto.FailRequest request){
            YogurtBatch batch = yogurtMakingService.markAsFailed(batchId);
            return ResponseEntity.ok(batch);
    }

    @Operation(summary = "Obtener todos los lotes de yogurt")
    @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Lotes de yogurt obtenidos correctamente"),
    @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })

    @GetMapping
    public ResponseEntity<@Nullable Object> getAllBatches(
        @RequestParam(required = false) YogurtBatch.BatchStatus status){
            if (status != null) {
                return ResponseEntity.ok(yogurtMakingService.getBatchesByStatus);
            }
            return ResponseEntity.ok(yogurtMakingService.getAllBatches);
        }


}
