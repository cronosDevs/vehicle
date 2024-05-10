package com.tech.vehicle.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class VehicleDto {
    private Long id;
    @NotNull(message = "El campo 'marca' no puede ser nulo")
    @NotBlank(message = "El campo 'marca' no puede estar vacío")
    private String marca;
    @NotNull(message = "El campo 'modelo' no puede ser nulo")
    @NotBlank(message = "El campo 'modelo' no puede estar vacío")
    private String modelo;
    @NotNull(message = "El campo 'matricula' no puede ser nulo")
    @NotBlank(message = "El campo 'matricula' no puede estar vacío")
    private String matricula;
    @NotNull(message = "El campo 'color' no puede ser nulo")
    @NotBlank(message = "El campo 'color' no puede estar vacío")
    private String color;
    @NotNull(message = "El campo 'anio' no puede ser nulo")
    private String anio;
}
