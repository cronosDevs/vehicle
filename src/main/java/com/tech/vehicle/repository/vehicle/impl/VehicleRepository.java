package com.tech.vehicle.repository.vehicle.impl;

import com.tech.vehicle.domain.VehicleDto;
import com.tech.vehicle.repository.entity.Vehicle;
import com.tech.vehicle.repository.vehicle.IVehicleRepository;
import com.tech.vehicle.repository.vehicle.specification.VehiculoSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class VehicleRepository implements IVehicleRepositoryFacade {

    private final IVehicleRepository vehicleRepository;

    /**
     * Finds all vehicles that match the provided criteria.
     *
     * @param vehicleDto The DTO containing the search criteria.
     * @param pageable   The pageable object for pagination and sorting.
     * @return A {@link Page} of {@link Vehicle} objects representing the found vehicles.
     */
    @Override
    public Page<Vehicle> findAll(VehicleDto vehicleDto, Pageable pageable) {
        Specification<Vehicle> specification = Specification.where(null);
        if (Objects.nonNull(vehicleDto.getMarca())) {
            specification = specification.and(VehiculoSpecification.porMarca(vehicleDto.getMarca()));
        }
        if (Objects.nonNull(vehicleDto.getModelo())) {
            specification = specification.and(VehiculoSpecification.porModelo(vehicleDto.getModelo()));
        }
        if (Objects.nonNull(vehicleDto.getMatricula())) {
            specification = specification.and(VehiculoSpecification.porMatricula(vehicleDto.getMatricula()));
        }
        if (Objects.nonNull(vehicleDto.getColor())) {
            specification = specification.and(VehiculoSpecification.porColor(vehicleDto.getColor()));
        }
        if (Objects.nonNull(vehicleDto.getAnio())) {
            specification = specification.and(VehiculoSpecification.porAnio(vehicleDto.getAnio()));
        }
        return vehicleRepository.findAll(specification, pageable);
    }

    /**
     * Finds a vehicle by its matricula.
     *
     * @param matricula The matricula (license plate) of the vehicle to find.
     * @return An {@link Optional} containing the {@link Vehicle} if found, or empty if not found.
     */
    @Override
    public Optional<Vehicle> findOneByMatricula(String matricula) {
        return vehicleRepository.findOneByMatricula(matricula);
    }

    /**
     * Finds a vehicle by its ID.
     *
     * @param id The ID of the vehicle to find.
     * @return An {@link Optional} containing the {@link Vehicle} if found, or empty if not found.
     */
    @Override
    public Optional<Vehicle> findOneById(Long id) {
        return vehicleRepository.findById(id);
    }

    /**
     * Creates a new vehicle in the database.
     *
     * @param vehicle The {@link Vehicle} object to create.
     * @return The created {@link Vehicle} object.
     */
    @Override
    public Vehicle create(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    /**
     * Deletes a vehicle from the database by its ID.
     *
     * @param id The ID of the vehicle to delete.
     */
    @Override
    public void delete(Long id) {
        vehicleRepository.deleteById(id);
    }
}
