package com.tech.vehicle.repository.vehicle.impl;

import com.tech.vehicle.domain.VehicleDto;
import com.tech.vehicle.repository.entity.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IVehicleRepositoryFacade {

    Page<Vehicle> findAll(VehicleDto vehicleDto, Pageable pageable);

    Optional<Vehicle> findOneByMatricula(String matricula);

    Optional<Vehicle> findOneById(Long id);

    Vehicle create(Vehicle vehicle);

    void delete(Long id);

}
