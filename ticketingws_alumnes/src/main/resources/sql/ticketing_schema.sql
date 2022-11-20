-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jun 18, 2022 at 02:38 PM
-- Server version: 8.0.29
-- PHP Version: 7.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `marianao`
--

-- --------------------------------------------------------

--
-- Table structure for table `actions`
--

CREATE TABLE `actions` (
  `id` bigint NOT NULL,
  `type` varchar(25) NOT NULL,
  `performer_username` varchar(25) NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ticket_id` bigint NOT NULL,
  `priority` int DEFAULT NULL,
  `technician_username` varchar(25) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `hours` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Table structure for table `rooms`
--

CREATE TABLE `rooms` (
  `id` bigint NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tickets`
--

CREATE TABLE `tickets` (
  `id` bigint NOT NULL,
  `category` varchar(25) NOT NULL,
  `description` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `username` varchar(25) NOT NULL,
  `role` varchar(25) NOT NULL,
  `full_name` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `extension` int DEFAULT NULL,
  `room_id` bigint DEFAULT NULL,
  `place` varchar(100) DEFAULT NULL,
  `company` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `actions`
--
ALTER TABLE `actions`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `ticket_x_date` (`ticket_id`,`date` DESC),
  ADD KEY `type` (`type`),
  ADD KEY `fk_performer_username` (`performer_username`),
  ADD KEY `fk_technician_username` (`technician_username`);

--
-- Indexes for table `rooms`
--
ALTER TABLE `rooms`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `tickets`
--
ALTER TABLE `tickets`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`username`),
  ADD KEY `role` (`role`),
  ADD KEY `full_name` (`full_name`),
  ADD KEY `role_x_full_name` (`role`,`full_name`),
  ADD KEY `fk_room_id` (`room_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `actions`
--
ALTER TABLE `actions`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `rooms`
--
ALTER TABLE `rooms`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tickets`
--
ALTER TABLE `tickets`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `actions`
--
ALTER TABLE `actions`
  ADD CONSTRAINT `fk_ticket_id` FOREIGN KEY (`ticket_id`) REFERENCES `tickets` (`id`),
  ADD CONSTRAINT `fk_performer_username` FOREIGN KEY (`performer_username`) REFERENCES `users` (`username`),
  ADD CONSTRAINT `fk_technician_username` FOREIGN KEY (`technician_username`) REFERENCES `users` (`username`);

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `fk_room_id` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
