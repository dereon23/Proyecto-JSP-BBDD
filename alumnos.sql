-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 18, 2023 at 09:40 PM
-- Server version: 10.4.25-MariaDB
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `alumnos`
--

-- --------------------------------------------------------

--
-- Table structure for table `alumnos`
--

CREATE TABLE `alumnos` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `apellido1` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `apellido2` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `dni` varchar(9) COLLATE utf8_spanish_ci NOT NULL,
  `grupo_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `alumnos`
--

INSERT INTO `alumnos` (`id`, `nombre`, `apellido1`, `apellido2`, `dni`, `grupo_id`) VALUES
(1, 'Manuel', 'Diaz', 'Marti', '34768523K', NULL),
(2, 'Ana', 'Sanchez', 'Garcia', '78547398L', 6),
(4, 'Elena', 'Martinez', 'Sanchez', '57835484N', NULL),
(7, 'Juan', 'Carrajo', 'Blanco', '56784375F', 5),
(8, 'Javier', 'Campos', 'Garcia', '54597498I', 5),
(9, 'Carlos', 'Hernandez', 'Echevarria', '57639893B', 5),
(10, 'Lucas', 'Nunez', 'Jimenez', '58467483J', 5),
(11, 'Javier', 'Sanchez', 'Carrero', '35783898J', NULL),
(13, 'Carlos', 'Gonzalez', 'Diaz', '45783298O', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `grupos`
--

CREATE TABLE `grupos` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `curso` int(11) NOT NULL,
  `tutor` varchar(50) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `grupos`
--

INSERT INTO `grupos` (`id`, `nombre`, `curso`, `tutor`) VALUES
(3, '1oBach A', 2022, 'Carlos Manuel Avila Rodriguez'),
(5, '1oBACH Cc', 2023, 'Maria Cespedes Rodriguez'),
(6, '2oBACH B', 2021, 'Gonzalo Menendez Castillo'),
(9, '4oESO B', 2022, 'Maria Jimenez Garcia');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `alumnos`
--
ALTER TABLE `alumnos`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `dni` (`dni`),
  ADD KEY `grupo_id` (`grupo_id`);

--
-- Indexes for table `grupos`
--
ALTER TABLE `grupos`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `alumnos`
--
ALTER TABLE `alumnos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `grupos`
--
ALTER TABLE `grupos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `alumnos`
--
ALTER TABLE `alumnos`
  ADD CONSTRAINT `alumnos_ibfk_1` FOREIGN KEY (`grupo_id`) REFERENCES `grupos` (`id`) ON DELETE SET NULL;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
