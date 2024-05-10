package com.tech.vehicle.service.vehicle;

import com.tech.vehicle.domain.VehicleDto;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface IVehicleService {

    List<VehicleDto> findAll(VehicleDto vehicleDto, Integer page,
                             Integer size, Sort.Direction order, String orderBy);

    VehicleDto createVehicle(VehicleDto vehicleDto);

    VehicleDto updateVehicle(Long vehicleId, VehicleDto vehicleDto);

    void deleteVehicle(Long vehicleId);
}
