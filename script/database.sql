CREATE DATABASE vehiculos;

CREATE TABLE VEHICULO
(
    ID        SERIAL PRIMARY KEY,
    MARCA     VARCHAR(255) NOT NULL,
    MODELO    VARCHAR(255) NOT NULL,
    MATRICULA VARCHAR(255) NOT NULL,
    COLOR     VARCHAR(255) NOT NULL,
    ANIO      VARCHAR(255) NOT NULL,
    CONSTRAINT matricula_unique UNIQUE (MATRICULA)
);

INSERT INTO VEHICULO (MARCA, MODELO, MATRICULA, COLOR, ANIO)
VALUES ('Toyota', 'Corolla', 'ABC123', 'Rojo', '2020'),
       ('Honda', 'Civic', 'DEF456', 'Azul', '2019'),
       ('Ford', 'Fiesta', 'GHI789', 'Blanco', '2018'),
       ('Chevrolet', 'Spark', 'JKL012', 'Gris', '2017'),
       ('Nissan', 'Sentra', 'MNO345', 'Negro', '2016'),
       ('Volkswagen', 'Jetta', 'PQR678', 'Plata', '2015'),
       ('Toyota', 'Yaris', 'STU901', 'Verde', '2014'),
       ('Hyundai', 'Accent', 'VWX234', 'Amarillo', '2013'),
       ('Ford', 'Focus', 'YZA567', 'Naranja', '2012'),
       ('Chevrolet', 'Cruze', 'BCD890', 'Morado', '2011'),
       ('Honda', 'Fit', 'EFG123', 'Rojo', '2010'),
       ('Volkswagen', 'Golf', 'HIJ456', 'Azul', '2009'),
       ('Nissan', 'Versa', 'KLM789', 'Blanco', '2008'),
       ('Hyundai', 'Elantra', 'NOP012', 'Gris', '2007'),
       ('Toyota', 'Camry', 'QRS345', 'Negro', '2006');
