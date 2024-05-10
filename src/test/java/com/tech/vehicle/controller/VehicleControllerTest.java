package com.tech.vehicle.controller;

import com.tech.vehicle.domain.VehicleDto;
import com.tech.vehicle.service.vehicle.IVehicleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class VehicleControllerTest {

    @InjectMocks
    private VehicleController vehicleController;

    @Mock
    private IVehicleService vehicleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testGetVehicles() {
        VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setMarca("Toyota");
        vehicleDto.setModelo("Corolla");
        List<VehicleDto> vehicleList = Collections.singletonList(vehicleDto);
        when(vehicleService.findAll(any(VehicleDto.class), anyInt(), anyInt(), any(), any())).thenReturn(vehicleList);
        ResponseEntity<List<VehicleDto>> response = vehicleController.getVehicles("Toyota", "Corolla", null, null, null, 0, 10, null, null);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(vehicleList, response.getBody());
    }

    @Test
    public void testCreateVehicle() {
        VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setMarca("Toyota");
        vehicleDto.setModelo("Corolla");
        when(vehicleService.createVehicle(any(VehicleDto.class))).thenReturn(vehicleDto);
        ResponseEntity<VehicleDto> response = vehicleController.createVehicle(vehicleDto);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(vehicleDto, response.getBody());
    }

    @Test
    public void testUpdateVehicle() {
        Long vehicleId = 1L;
        VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setMarca("Toyota");
        vehicleDto.setModelo("Corolla");
        when(vehicleService.updateVehicle(eq(vehicleId), any(VehicleDto.class))).thenReturn(vehicleDto);
        ResponseEntity<VehicleDto> response = vehicleController.updateVehicle(vehicleDto, vehicleId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(vehicleDto, response.getBody());
    }

    @Test
    public void testDeleteVehicle() {
        Long vehicleId = 1L;
        ResponseEntity<Void> response = vehicleController.deleteVehicle(vehicleId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }
}