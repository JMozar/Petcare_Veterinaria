-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 26-11-2022 a las 02:35:28
-- Versión del servidor: 8.0.29
-- Versión de PHP: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `petcare`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleados`
--

CREATE TABLE `empleados` (
  `idEmpleado` int NOT NULL,
  `nombre` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `apellido` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `DNI` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `celular` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `especialidad` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `sueldo` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `empleados`
--

INSERT INTO `empleados` (`idEmpleado`, `nombre`, `apellido`, `DNI`, `celular`, `especialidad`, `sueldo`) VALUES
(1, 'Edgar', 'Suarez', '77543333', '999111222', 'Veterinario', 1500);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `familias`
--

CREATE TABLE `familias` (
  `idFamilia` int NOT NULL,
  `apellido` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `num_Cuenta` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `direccion` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `celular` varchar(45) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `familias`
--

INSERT INTO `familias` (`idFamilia`, `apellido`, `num_Cuenta`, `direccion`, `celular`) VALUES
(2, 'Salazar', '123456789', 'Mz B Lt 11', '999666555'),
(4, 'Moore', '123456789', 'Mz B lt 11', '999888777');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mascotas`
--

CREATE TABLE `mascotas` (
  `idMascota` int NOT NULL,
  `nombre` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `especie` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `raza` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `color` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `fecha_Nacimiento` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `talla` float NOT NULL,
  `peso` float NOT NULL,
  `idFamilia` int NOT NULL DEFAULT '-1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `mascotas`
--

INSERT INTO `mascotas` (`idMascota`, `nombre`, `especie`, `raza`, `color`, `fecha_Nacimiento`, `talla`, `peso`, `idFamilia`) VALUES
(1, 'Peque', 'Perro', 'Chiwawa', 'Mostaza', '11-11-11', 0, 0, -1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `personas`
--

CREATE TABLE `personas` (
  `idPersona` int NOT NULL,
  `nombre` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `apellido` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `DNI` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `celular` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `idFamilia` int NOT NULL DEFAULT '-1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `personas`
--

INSERT INTO `personas` (`idPersona`, `nombre`, `apellido`, `DNI`, `celular`, `idFamilia`) VALUES
(1, 'Carlos', 'Ramos', '77766655', '999888777', -1),
(2, 'Jhon', 'Moore', '72388776', '999111222', -1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `idUsuario` int NOT NULL,
  `usuario` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `contraseña` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `ultima_Sesion` timestamp NOT NULL,
  `fecha_Registro` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`idUsuario`, `usuario`, `contraseña`, `ultima_Sesion`, `fecha_Registro`) VALUES
(1, 'admin', '123', '2022-11-25 22:41:54', '2022-11-01');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `empleados`
--
ALTER TABLE `empleados`
  ADD PRIMARY KEY (`idEmpleado`);

--
-- Indices de la tabla `familias`
--
ALTER TABLE `familias`
  ADD PRIMARY KEY (`idFamilia`);

--
-- Indices de la tabla `mascotas`
--
ALTER TABLE `mascotas`
  ADD PRIMARY KEY (`idMascota`);

--
-- Indices de la tabla `personas`
--
ALTER TABLE `personas`
  ADD PRIMARY KEY (`idPersona`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`idUsuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `empleados`
--
ALTER TABLE `empleados`
  MODIFY `idEmpleado` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `familias`
--
ALTER TABLE `familias`
  MODIFY `idFamilia` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `mascotas`
--
ALTER TABLE `mascotas`
  MODIFY `idMascota` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `personas`
--
ALTER TABLE `personas`
  MODIFY `idPersona` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `idUsuario` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
