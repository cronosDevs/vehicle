package com.tech.vehicle.repository.vehicle.specification;

import com.tech.vehicle.repository.entity.Vehicle;
import org.springframework.data.jpa.domain.Specification;

public class VehiculoSpecification {

    public static Specification<Vehicle> porMarca(String marca) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("marca"), marca);
    }

    public static Specification<Vehicle> porModelo(String modelo) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("modelo"), modelo);
    }

    public static Specification<Vehicle> porMatricula(String matricula) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("matricula"), matricula);
    }

    public static Specification<Vehicle> porColor(String color) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("color"), color);
    }

    public static Specification<Vehicle> porAnio(String anio) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("anio"), anio);
    }

}
