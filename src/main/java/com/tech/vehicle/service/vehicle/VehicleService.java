package com.tech.vehicle.service.vehicle;

import com.tech.vehicle.domain.VehicleDto;
import com.tech.vehicle.repository.entity.Vehicle;
import com.tech.vehicle.repository.vehicle.impl.IVehicleRepositoryFacade;
import com.tech.vehicle.util.MergeObjects;
import com.tech.vehicle.util.exception.domain.BadRequestException;
import com.tech.vehicle.util.exception.domain.NotFoundException;
import com.tech.vehicle.util.mapper.VehicleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VehicleService implements IVehicleService {

    private final IVehicleRepositoryFacade vehicleRepositoryFacade;
    private final VehicleMapper vehicleMapper;

    /**
     * Finds all vehicles that match the provided criteria.
     *
     * @param vehicleDto The DTO containing the search criteria.
     * @param page       The page number.
     * @param size       The page size.
     * @param order      The sorting direction (ASC or DESC).
     * @param orderBy    The field to order by.
     * @return A list of {@link VehicleDto} objects representing the found vehicles.
     */
    @Override
    public List<VehicleDto> findAll(VehicleDto vehicleDto, Integer page, Integer size, Sort.Direction order, String orderBy) {
        Pageable pageable = createPageable(page, size, order, orderBy);
        Page<Vehicle> vehicleList = vehicleRepositoryFacade.findAll(vehicleDto, pageable);
        return vehicleMapper.vehicleListToVehicleDtoList(vehicleList.getContent());
    }

    /**
     * Creates a new vehicle.
     *
     * @param vehicleDto The DTO containing the vehicle information.
     * @return The created {@link VehicleDto} object.
     */
    @Override
    public VehicleDto createVehicle(VehicleDto vehicleDto) {
        checkMatriculaAlreadyExist(vehicleDto);
        Vehicle vehicle = vehicleMapper.vehicleDtoToVehicle(vehicleDto);
        return vehicleMapper.vehicleToVehicleDto(vehicleRepositoryFacade.create(vehicle));
    }

    /**
     * Updates an existing vehicle.
     *
     * @param vehicleId  The ID of the vehicle to update.
     * @param vehicleDto The DTO containing the updated vehicle information.
     * @return The updated {@link VehicleDto} object.
     */
    @Override
    public VehicleDto updateVehicle(Long vehicleId, VehicleDto vehicleDto) {
        Optional<Vehicle> vehicleOptional = vehicleRepositoryFacade.findOneById(vehicleId);
        if (vehicleOptional.isPresent()) {
            checkUpdateMatricula(vehicleOptional.get(), vehicleDto);
            MergeObjects.mergeObjects(vehicleDto, vehicleOptional.get());
            vehicleOptional.get().setId(vehicleId);
            return vehicleMapper.vehicleToVehicleDto(vehicleRepositoryFacade.create(vehicleOptional.get()));
        }
        throw new NotFoundException("El vehiculo no existe", null);
    }

    /**
     * Deletes a vehicle.
     *
     * @param vehicleId The ID of the vehicle to delete.
     */
    @Override
    public void deleteVehicle(Long vehicleId) {
        Optional<Vehicle> vehicleOptional = vehicleRepositoryFacade.findOneById(vehicleId);
        if (!vehicleOptional.isPresent()) {
            throw new NotFoundException("El vehiculo no existe", null);
        }
        vehicleRepositoryFacade.delete(vehicleId);
    }

    /**
     * Checks if the updated matricula in the {@code vehicleDto} is different from the matricula in the {@code vehicleDatabase}.
     * If it is different, it checks if the new matricula already exists in the database.
     *
     * @param vehicleDatabase The {@link Vehicle} entity from the database.
     * @param vehicleDto      The {@link VehicleDto} containing the updated matricula.
     */
    public void checkUpdateMatricula(Vehicle vehicleDatabase, VehicleDto vehicleDto) {
        if (Objects.nonNull(vehicleDto.getMatricula()) && !vehicleDatabase.getMatricula().equalsIgnoreCase(vehicleDto.getMatricula())) {
            checkMatriculaAlreadyExist(vehicleDto);
        }
    }

    /**
     * Creates a pageable object for pagination with optional sorting.
     *
     * @param page    The page number.
     * @param size    The page size.
     * @param order   The sorting direction (ASC or DESC).
     * @param orderBy The field to order by.
     * @return A {@link Pageable} object for pagination and sorting.
     */
    public Pageable createPageable(Integer page, Integer size, Sort.Direction order, String orderBy) {
        if (Objects.nonNull(order) && Objects.nonNull(orderBy)) {
            Sort sort = Sort.by(order, orderBy);
            return PageRequest.of(page, size, sort);
        }
        return PageRequest.of(page, size);
    }

    /**
     * Checks if the matricula already exists in the database.
     * If it exists, throws a {@link BadRequestException} with an appropriate message.
     *
     * @param vehicleDto The {@link VehicleDto} containing the matricula to check.
     * @throws BadRequestException If the matricula already exists in the database.
     */
    public void checkMatriculaAlreadyExist(VehicleDto vehicleDto) {
        Optional<Vehicle> vehicleOptional = vehicleRepositoryFacade.findOneByMatricula(vehicleDto.getMatricula());
        if (vehicleOptional.isPresent()) {
            throw new BadRequestException("La matricula ya existe", null);
        }
    }
}
