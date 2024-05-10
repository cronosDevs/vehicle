package com.tech.vehicle.service;

import com.tech.vehicle.domain.VehicleDto;
import com.tech.vehicle.repository.entity.Vehicle;
import com.tech.vehicle.repository.vehicle.impl.IVehicleRepositoryFacade;
import com.tech.vehicle.service.vehicle.VehicleService;
import com.tech.vehicle.util.exception.domain.BadRequestException;
import com.tech.vehicle.util.exception.domain.NotFoundException;
import com.tech.vehicle.util.mapper.VehicleMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class VehicleServiceTest {

    @Mock
    private IVehicleRepositoryFacade vehicleRepositoryFacade;

    @Mock
    private VehicleMapper vehicleMapper;

    @InjectMocks
    private VehicleService vehicleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        VehicleDto vehicleDto = new VehicleDto();
        PageRequest pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "id"));
        Vehicle vehicle1 = new Vehicle();
        Vehicle vehicle2 = new Vehicle();
        Page<Vehicle> vehiclePage = new PageImpl<>(Arrays.asList(vehicle1, vehicle2));

        when(vehicleRepositoryFacade.findAll(vehicleDto, pageable)).thenReturn(vehiclePage);

        assertEquals(0, vehicleService.findAll(vehicleDto, 0, 10, Sort.Direction.ASC, "id").size());
        verify(vehicleMapper, times(1)).vehicleListToVehicleDtoList(anyList());
    }

    @Test
    void testCreateVehicle() {
        VehicleDto vehicleDto = new VehicleDto();
        Vehicle vehicle = new Vehicle();

        when(vehicleMapper.vehicleDtoToVehicle(vehicleDto)).thenReturn(vehicle);
        when(vehicleRepositoryFacade.create(vehicle)).thenReturn(vehicle);
        when(vehicleMapper.vehicleToVehicleDto(vehicle)).thenReturn(vehicleDto);

        assertEquals(vehicleDto, vehicleService.createVehicle(vehicleDto));
        verify(vehicleRepositoryFacade, times(1)).create(vehicle);
    }

    @Test
    void testUpdateVehicle() {
        Long vehicleId = 1L;
        VehicleDto vehicleDto = new VehicleDto();
        Vehicle vehicle = new Vehicle();
        vehicle.setId(vehicleId);

        when(vehicleRepositoryFacade.findOneById(vehicleId)).thenReturn(Optional.of(vehicle));
        when(vehicleRepositoryFacade.create(vehicle)).thenReturn(vehicle);
        when(vehicleMapper.vehicleToVehicleDto(vehicle)).thenReturn(vehicleDto);

        assertEquals(vehicleDto, vehicleService.updateVehicle(vehicleId, vehicleDto));
        verify(vehicleRepositoryFacade, times(1)).create(vehicle);
    }

    @Test
    void testUpdateVehicleNotFound() {
        Long vehicleId = 1L;
        VehicleDto vehicleDto = new VehicleDto();

        when(vehicleRepositoryFacade.findOneById(vehicleId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> vehicleService.updateVehicle(vehicleId, vehicleDto));
        verify(vehicleRepositoryFacade, never()).create(any());
    }

    @Test
    void testDeleteVehicle() {
        Long vehicleId = 1L;
        Vehicle vehicle = new Vehicle();

        when(vehicleRepositoryFacade.findOneById(vehicleId)).thenReturn(Optional.of(vehicle));

        vehicleService.deleteVehicle(vehicleId);

        verify(vehicleRepositoryFacade, times(1)).delete(vehicleId);
    }

    @Test
    void testDeleteVehicleNotFound() {
        Long vehicleId = 1L;
        when(vehicleRepositoryFacade.findOneById(vehicleId)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> vehicleService.deleteVehicle(vehicleId));
        verify(vehicleRepositoryFacade, never()).delete(any());
    }

    @Test
    void testCheckUpdateMatricula() {
        Vehicle vehicle = new Vehicle();
        vehicle.setMatricula("ABC123");
        VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setMatricula("ABC123");
        vehicleService.checkUpdateMatricula(vehicle, vehicleDto);
        assertEquals("ABC123", vehicle.getMatricula());
    }

    @Test
    void testCheckUpdateMatriculaDifferentMatricula() {
        Vehicle vehicle = new Vehicle();
        vehicle.setMatricula("XYZ456");
        VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setMatricula("ABC123");
        vehicleDto.setId(1L);
        when(vehicleRepositoryFacade.findOneByMatricula("ABC123")).thenReturn(Optional.of(new Vehicle()));

        assertThrows(BadRequestException.class, () -> vehicleService.checkUpdateMatricula(vehicle, vehicleDto));
    }

    @Test
    void testCheckUpdateMatriculaSameMatricula() {
        Vehicle vehicle = new Vehicle();
        vehicle.setMatricula("ABC123");
        VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setMatricula("ABC123");
        vehicleService.checkUpdateMatricula(vehicle, vehicleDto);
        assertEquals("ABC123", vehicle.getMatricula());
    }

    @Test
    void testCheckMatriculaAlreadyExist() {
        VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setMatricula("ABC123");
        when(vehicleRepositoryFacade.findOneByMatricula("ABC123")).thenReturn(Optional.of(new Vehicle()));
        assertThrows(BadRequestException.class, () -> vehicleService.checkMatriculaAlreadyExist(vehicleDto));
    }

}
