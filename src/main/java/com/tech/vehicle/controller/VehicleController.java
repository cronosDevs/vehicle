package com.tech.vehicle.controller;


import com.tech.vehicle.domain.VehicleDto;
import com.tech.vehicle.service.vehicle.IVehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class VehicleController {

    private final IVehicleService vehicleService;

    @Operation(summary = "Get list of vehicles")
    @GetMapping("/vehicles")
    public ResponseEntity<List<VehicleDto>> getVehicles(
            @RequestParam(required = false) String marca,
            @RequestParam(required = false) String modelo,
            @RequestParam(required = false) String matricula,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) String anio,
            @Parameter(description = "Page number") @RequestParam Integer page,
            @Parameter(description = "Page size") @RequestParam Integer size,
            @Parameter(description = "Sorting direction (ASC or DESC)", schema = @Schema(allowableValues = {"ASC", "DESC"}))
            @RequestParam(required = false) Sort.Direction order,
            @Parameter(description = "Field to order by") @RequestParam(required = false) String orderBy) {
        VehicleDto vehicleDto = VehicleDto.builder()
                .marca(marca)
                .modelo(modelo)
                .matricula(matricula)
                .color(color)
                .anio(anio)
                .build();
        return ResponseEntity.ok(vehicleService.findAll(vehicleDto, page, size, order, orderBy));
    }

    @Operation(summary = "Create a new vehicle")
    @PostMapping("/vehicles")
    public ResponseEntity<VehicleDto> createVehicle(@Valid @RequestBody VehicleDto vehicleDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vehicleService.createVehicle(vehicleDto));
    }

    @Operation(summary = "Update an existing vehicle")
    @PatchMapping("/vehicles/{vehicleId}")
    public ResponseEntity<VehicleDto> updateVehicle(
            @Parameter(description = "Updated vehicle information") @RequestBody VehicleDto vehicleDto,
            @Parameter(description = "ID of the vehicle to update") @PathVariable Long vehicleId) {
        return ResponseEntity.ok(vehicleService.updateVehicle(vehicleId, vehicleDto));
    }

    @Operation(summary = "Delete a vehicle")
    @DeleteMapping("/vehicles/{vehicleId}")
    public ResponseEntity<Void> deleteVehicle(
            @Parameter(description = "ID of the vehicle to delete") @PathVariable Long vehicleId) {
        vehicleService.deleteVehicle(vehicleId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
