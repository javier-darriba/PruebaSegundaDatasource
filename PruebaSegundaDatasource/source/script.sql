CREATE SCHEMA `bdalmacen`;
USE bdalmacen;
CREATE TABLE `bdalmacen`.`productos` (
  `nombre` VARCHAR(45) NOT NULL,
  `categoria` VARCHAR(45) NOT NULL,
  `precio` DECIMAL(10,2) NOT NULL,
  `stock` INT NULL);
