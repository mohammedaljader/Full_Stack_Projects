-- phpMyAdmin SQL Dump
-- version 4.9.3
-- https://www.phpmyadmin.net/
--
-- Host: studmysql01.fhict.local
-- Generation Time: Jan 21, 2022 at 05:40 PM
-- Server version: 5.7.26-log
-- PHP Version: 7.4.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dbi454066`
--

-- --------------------------------------------------------

--
-- Table structure for table `ink_usage_over_time`
--

DROP TABLE IF EXISTS `ink_usage_over_time`;
CREATE TABLE `ink_usage_over_time` (
  `rID` int(11) NOT NULL,
  `machine_id` varchar(255) NOT NULL,
  `date` datetime NOT NULL,
  `color_black` float NOT NULL,
  `color_cyan` float NOT NULL,
  `color_magenta` float NOT NULL,
  `color_yellow` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `media_category_over_time`
--

DROP TABLE IF EXISTS `media_category_over_time`;
CREATE TABLE `media_category_over_time` (
  `rID` int(11) NOT NULL,
  `machine_id` varchar(255) NOT NULL,
  `date` datetime NOT NULL,
  `Paper` float NOT NULL DEFAULT '0',
  `LightPaper` float NOT NULL DEFAULT '0',
  `HeavyPaper` float NOT NULL DEFAULT '0',
  `Film` float NOT NULL DEFAULT '0',
  `ThickFilm` float NOT NULL DEFAULT '0',
  `LightBanner` float NOT NULL DEFAULT '0',
  `HeavyBanner` float NOT NULL DEFAULT '0',
  `Textile` float NOT NULL DEFAULT '0',
  `MonomericVinyl` float NOT NULL DEFAULT '0',
  `Canvas` float NOT NULL DEFAULT '0',
  `PolymericCastVinyl` float NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `sqm_per_print_mode`
--

DROP TABLE IF EXISTS `sqm_per_print_mode`;
CREATE TABLE `sqm_per_print_mode` (
  `rID` int(11) NOT NULL,
  `machine_id` int(11) NOT NULL,
  `date` datetime NOT NULL,
  `1_pass` int(11) NOT NULL DEFAULT '0',
  `1_pass_highDensity` int(11) NOT NULL DEFAULT '0',
  `2_pass` int(11) NOT NULL DEFAULT '0',
  `4_pass` int(11) NOT NULL DEFAULT '0',
  `8_pass` int(11) NOT NULL DEFAULT '0',
  `8_pass_highDensity` int(11) NOT NULL DEFAULT '0',
  `16_pass` int(11) NOT NULL DEFAULT '0',
  `other` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Stand-in structure for view `top_10_machines_print_volume`
-- (See below for the actual view)
--
DROP VIEW IF EXISTS `top_10_machines_print_volume`;
CREATE TABLE `top_10_machines_print_volume` (
`machine_id` int(11)
,`SUM` decimal(39,0)
);

-- --------------------------------------------------------

--
-- Table structure for table `traversed`
--

DROP TABLE IF EXISTS `traversed`;
CREATE TABLE `traversed` (
  `path` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `used_media_type`
--

DROP TABLE IF EXISTS `used_media_type`;
CREATE TABLE `used_media_type` (
  `machine_id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `area` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure for view `top_10_machines_print_volume`
--
DROP TABLE IF EXISTS `top_10_machines_print_volume`;

CREATE ALGORITHM=UNDEFINED DEFINER=`dbi454066`@`%` SQL SECURITY DEFINER VIEW `top_10_machines_print_volume`  AS  select `sqm_per_print_mode`.`machine_id` AS `machine_id`,(((((((sum(`sqm_per_print_mode`.`1_pass`) + sum(`sqm_per_print_mode`.`2_pass`)) + sum(`sqm_per_print_mode`.`1_pass_highDensity`)) + sum(`sqm_per_print_mode`.`4_pass`)) + sum(`sqm_per_print_mode`.`8_pass`)) + sum(`sqm_per_print_mode`.`8_pass_highDensity`)) + sum(`sqm_per_print_mode`.`16_pass`)) + sum(`sqm_per_print_mode`.`other`)) AS `SUM` from `sqm_per_print_mode` group by `sqm_per_print_mode`.`machine_id` order by `SUM` desc limit 10 ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `ink_usage_over_time`
--
ALTER TABLE `ink_usage_over_time`
  ADD PRIMARY KEY (`rID`);

--
-- Indexes for table `media_category_over_time`
--
ALTER TABLE `media_category_over_time`
  ADD PRIMARY KEY (`rID`);

--
-- Indexes for table `sqm_per_print_mode`
--
ALTER TABLE `sqm_per_print_mode`
  ADD PRIMARY KEY (`rID`);

--
-- Indexes for table `traversed`
--
ALTER TABLE `traversed`
  ADD PRIMARY KEY (`path`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `ink_usage_over_time`
--
ALTER TABLE `ink_usage_over_time`
  MODIFY `rID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `media_category_over_time`
--
ALTER TABLE `media_category_over_time`
  MODIFY `rID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `sqm_per_print_mode`
--
ALTER TABLE `sqm_per_print_mode`
  MODIFY `rID` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
