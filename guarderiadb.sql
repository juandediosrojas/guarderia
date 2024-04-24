CREATE DATABASE `guarderiadb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

CREATE TABLE `cliente` (
  `dk` int NOT NULL AUTO_INCREMENT,
  `identificacion` varchar(45) DEFAULT NULL,
  `nombres_cliente` varchar(45) DEFAULT NULL,
  `apellidos_cliente` varchar(45) DEFAULT NULL,
  `direccion` varchar(100) DEFAULT NULL,
  `correo` varchar(45) DEFAULT NULL,
  `celular` bigint DEFAULT NULL,
  PRIMARY KEY (`dk`),
  UNIQUE KEY `identificacion_cliente_UNIQUE` (`identificacion`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `empleado` (
  `dk` int NOT NULL AUTO_INCREMENT,
  `identificacion_empleado` varchar(10) DEFAULT NULL,
  `nombres_empleado` varchar(45) DEFAULT NULL,
  `apellidos_empleado` varchar(45) DEFAULT NULL,
  `direccion` varchar(100) DEFAULT NULL,
  `correo` varchar(45) DEFAULT NULL,
  `celular` int DEFAULT NULL,
  PRIMARY KEY (`dk`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `mascotas` (
  `dk` int NOT NULL AUTO_INCREMENT,
  `identifiacion` int DEFAULT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `especie` varchar(45) DEFAULT NULL,
  `raza` varchar(45) DEFAULT NULL,
  `cliente` int DEFAULT NULL,
  PRIMARY KEY (`dk`),
  UNIQUE KEY `identifiacion_mascota_UNIQUE` (`identifiacion`),
  KEY `cliente_fk_idx` (`cliente`),
  CONSTRAINT `cliente_fk` FOREIGN KEY (`cliente`) REFERENCES `cliente` (`dk`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `registros` (
  `dk` int NOT NULL AUTO_INCREMENT,
  `mascota_fk` int NOT NULL,
  `fecha_entrada` date DEFAULT NULL,
  `fecha_salida` date DEFAULT NULL,
  PRIMARY KEY (`dk`),
  KEY `mascota_fk_idx` (`mascota_fk`),
  CONSTRAINT `mascotaregistro_fk` FOREIGN KEY (`mascota_fk`) REFERENCES `mascotas` (`dk`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `usuarios` (
  `dk` int NOT NULL AUTO_INCREMENT,
  `usuario` varchar(45) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `empleado_fk` int DEFAULT NULL,
  PRIMARY KEY (`dk`),
  KEY `empleado_fk_idx` (`empleado_fk`),
  CONSTRAINT `empleado_fk` FOREIGN KEY (`empleado_fk`) REFERENCES `empleado` (`dk`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;