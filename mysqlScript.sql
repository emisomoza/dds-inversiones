-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Servidor: db
-- Tiempo de generación: 08-10-2017 a las 13:38:51
-- Versión del servidor: 5.7.19
-- Versión de PHP: 7.0.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `INVERSIONES`
--
CREATE DATABASE IF NOT EXISTS `INVERSIONES` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `INVERSIONES`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `CUENTA`
--

DROP TABLE IF EXISTS `CUENTA`;
CREATE TABLE `CUENTA` (
  `empresa_id` int(11) NOT NULL,
  `periodo_id` int(11) NOT NULL,
  `cuenta_tipo` int(11) NOT NULL,
  `cuenta_valor` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `EMPRESA`
--

DROP TABLE IF EXISTS `EMPRESA`;
CREATE TABLE `EMPRESA` (
  `empresa_id` int(11) NOT NULL,
  `empresa_nombre` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `PERIODO`
--

DROP TABLE IF EXISTS `PERIODO`;
CREATE TABLE `PERIODO` (
  `periodo_id` int(11) NOT NULL,
  `fecha_inicio` date NOT NULL,
  `fecha_fin` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `TIPO_CUENTA`
--

DROP TABLE IF EXISTS `TIPO_CUENTA`;
CREATE TABLE `TIPO_CUENTA` (
  `tipo_id` int(11) NOT NULL,
  `tipo_descripcion` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `CUENTA`
--
ALTER TABLE `CUENTA`
  ADD PRIMARY KEY (`empresa_id`,`periodo_id`,`cuenta_tipo`),
  ADD KEY `cuenta_periodo` (`periodo_id`),
  ADD KEY `cuenta_tipo` (`cuenta_tipo`);

--
-- Indices de la tabla `EMPRESA`
--
ALTER TABLE `EMPRESA`
  ADD PRIMARY KEY (`empresa_id`);

--
-- Indices de la tabla `PERIODO`
--
ALTER TABLE `PERIODO`
  ADD PRIMARY KEY (`periodo_id`);

--
-- Indices de la tabla `TIPO_CUENTA`
--
ALTER TABLE `TIPO_CUENTA`
  ADD PRIMARY KEY (`tipo_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `EMPRESA`
--
ALTER TABLE `EMPRESA`
  MODIFY `empresa_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `PERIODO`
--
ALTER TABLE `PERIODO`
  MODIFY `periodo_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `TIPO_CUENTA`
--
ALTER TABLE `TIPO_CUENTA`
  MODIFY `tipo_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `CUENTA`
--
ALTER TABLE `CUENTA`
  ADD CONSTRAINT `cuenta_empresa` FOREIGN KEY (`empresa_id`) REFERENCES `EMPRESA` (`empresa_id`),
  ADD CONSTRAINT `cuenta_periodo` FOREIGN KEY (`periodo_id`) REFERENCES `PERIODO` (`periodo_id`),
  ADD CONSTRAINT `cuenta_tipo` FOREIGN KEY (`cuenta_tipo`) REFERENCES `TIPO_CUENTA` (`tipo_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
