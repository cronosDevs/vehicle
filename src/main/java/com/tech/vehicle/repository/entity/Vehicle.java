package com.tech.vehicle.repository.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "VEHICULO")
@Data
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String marca;
    private String modelo;
    private String matricula;
    private String color;
    private String anio;
}
