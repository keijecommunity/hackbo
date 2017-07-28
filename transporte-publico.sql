-- phpMyAdmin SQL Dump
-- version 4.3.11
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 27-07-2017 a las 23:52:34
-- Versión del servidor: 5.6.24
-- Versión de PHP: 5.6.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `transporte-publico`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `coordenada`
--

CREATE TABLE IF NOT EXISTS `coordenada` (
  `id` int(11) NOT NULL,
  `lat` double NOT NULL,
  `lon` double NOT NULL,
  `titulo` varchar(9999) NOT NULL,
  `idi` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=384 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `coordenada`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `coordenadai`
--

CREATE TABLE IF NOT EXISTS `coordenadai` (
  `id` int(11) NOT NULL,
  `lat` double NOT NULL,
  `lon` double NOT NULL,
  `titulo` varchar(999) NOT NULL,
  `tipo` varchar(999) NOT NULL,
  `d` double NOT NULL,
  `descr` varchar(9999) NOT NULL,
  `cod` varchar(9999) NOT NULL,
  `fam` varchar(9999) NOT NULL,
  `dim` varchar(9999) NOT NULL,
  `est` varchar(9999) NOT NULL,
  `pro` varchar(9999) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=latin1;

--

--
ALTER TABLE `coordenada`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `coordenadai`
--
ALTER TABLE `coordenadai`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `coordenada`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=384;

ALTER TABLE `coordenadai`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=60;

