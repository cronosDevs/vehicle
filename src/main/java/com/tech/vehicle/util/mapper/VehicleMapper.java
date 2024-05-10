package com.tech.vehicle.util.mapper;

import com.tech.vehicle.domain.VehicleDto;
import com.tech.vehicle.repository.entity.Vehicle;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface VehicleMapper {

    VehicleDto vehicleToVehicleDto(Vehicle vehicle);

    @Mappings({
            @Mapping(target = "id", ignore = true),
    })
    Vehicle vehicleDtoToVehicle(VehicleDto vehicle);

    List<VehicleDto> vehicleListToVehicleDtoList(List<Vehicle> vehicle);

}
