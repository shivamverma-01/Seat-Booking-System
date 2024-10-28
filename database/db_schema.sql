-- Create the reservation database
CREATE DATABASE IF NOT EXISTS reservation_db;

-- Use the newly created database
USE reservation_db;

-- Create the buses table
CREATE TABLE `buses` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `bus_no` varchar(255) DEFAULT NULL,
  `total_seats` int NOT NULL,
  PRIMARY KEY (`id`)
);

-- Create the customers table
CREATE TABLE `customers` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `bus_no` varchar(255) DEFAULT NULL,
  `contact_number` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `seat_no` int NOT NULL,
  PRIMARY KEY (`id`)
);

-- Create the seats table
CREATE TABLE `seats` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `bus_no` varchar(255) DEFAULT NULL,
  `is_locked` bit(1) NOT NULL,
  `is_reserved` bit(1) NOT NULL,
  `seat_no` int NOT NULL,
  PRIMARY KEY (`id`)
);


-- Insert sample data into the bus table
INSERT INTO `buses` (`bus_no`, `total_seats`)
VALUES
  ('bus001', 40),
  ('bus002', 40),
  ('bus003', 40),
  ('bus004', 40);


-- Insert sample data into the seats table for multiple buses
INSERT INTO `seats` (`bus_no`, `is_locked`, `is_reserved`, `seat_no`)
VALUES
  -- Seats for bus001
  ('bus001', 0, 0, 1), ('bus001', 0, 0, 2), ('bus001', 0, 0, 3), ('bus001', 0, 0, 4),
  ('bus001', 0, 0, 5), ('bus001', 0, 0, 6), ('bus001', 0, 0, 7), ('bus001', 0, 0, 8),
  ('bus001', 0, 0, 9), ('bus001', 0, 0, 10), ('bus001', 0, 0, 11), ('bus001', 0, 0, 12),
  ('bus001', 0, 0, 13), ('bus001', 0, 0, 14), ('bus001', 0, 0, 15), ('bus001', 0, 0, 16),
  ('bus001', 0, 0, 17), ('bus001', 0, 0, 18), ('bus001', 0, 0, 19), ('bus001', 0, 0, 20),
  ('bus001', 0, 0, 21), ('bus001', 0, 0, 22), ('bus001', 0, 0, 23), ('bus001', 0, 0, 24),
  ('bus001', 0, 0, 25), ('bus001', 0, 0, 26), ('bus001', 0, 0, 27), ('bus001', 0, 0, 28),
  ('bus001', 0, 0, 29), ('bus001', 0, 0, 30), ('bus001', 0, 0, 31), ('bus001', 0, 0, 32),
  ('bus001', 0, 0, 33), ('bus001', 0, 0, 34), ('bus001', 0, 0, 35), ('bus001', 0, 0, 36),
  ('bus001', 0, 0, 37), ('bus001', 0, 0, 38), ('bus001', 0, 0, 39), ('bus001', 0, 0, 40),

  -- Seats for bus002
  ('bus002', 0, 0, 1), ('bus002', 0, 0, 2), ('bus002', 0, 0, 3), ('bus002', 0, 0, 4),
  ('bus002', 0, 0, 5), ('bus002', 0, 0, 6), ('bus002', 0, 0, 7), ('bus002', 0, 0, 8),
  ('bus002', 0, 0, 9), ('bus002', 0, 0, 10), ('bus002', 0, 0, 11), ('bus002', 0, 0, 12),
  ('bus002', 0, 0, 13), ('bus002', 0, 0, 14), ('bus002', 0, 0, 15), ('bus002', 0, 0, 16),
  ('bus002', 0, 0, 17), ('bus002', 0, 0, 18), ('bus002', 0, 0, 19), ('bus002', 0, 0, 20),
  ('bus002', 0, 0, 21), ('bus002', 0, 0, 22), ('bus002', 0, 0, 23), ('bus002', 0, 0, 24),
  ('bus002', 0, 0, 25), ('bus002', 0, 0, 26), ('bus002', 0, 0, 27), ('bus002', 0, 0, 28),
  ('bus002', 0, 0, 29), ('bus002', 0, 0, 30), ('bus002', 0, 0, 31), ('bus002', 0, 0, 32),
  ('bus002', 0, 0, 33), ('bus002', 0, 0, 34), ('bus002', 0, 0, 35), ('bus002', 0, 0, 36),
  ('bus002', 0, 0, 37), ('bus002', 0, 0, 38), ('bus002', 0, 0, 39), ('bus002', 0, 0, 40),

  -- Seats for bus003
  ('bus003', 0, 0, 1), ('bus003', 0, 0, 2), ('bus003', 0, 0, 3), ('bus003', 0, 0, 4),
  ('bus003', 0, 0, 5), ('bus003', 0, 0, 6), ('bus003', 0, 0, 7), ('bus003', 0, 0, 8),
  ('bus003', 0, 0, 9), ('bus003', 0, 0, 10), ('bus003', 0, 0, 11), ('bus003', 0, 0, 12),
  ('bus003', 0, 0, 13), ('bus003', 0, 0, 14), ('bus003', 0, 0, 15), ('bus003', 0, 0, 16),
  ('bus003', 0, 0, 17), ('bus003', 0, 0, 18), ('bus003', 0, 0, 19), ('bus003', 0, 0, 20),
  ('bus003', 0, 0, 21), ('bus003', 0, 0, 22), ('bus003', 0, 0, 23), ('bus003', 0, 0, 24),
  ('bus003', 0, 0, 25), ('bus003', 0, 0, 26), ('bus003', 0, 0, 27), ('bus003', 0, 0, 28),
  ('bus003', 0, 0, 29), ('bus003', 0, 0, 30), ('bus003', 0, 0, 31), ('bus003', 0, 0, 32),
  ('bus003', 0, 0, 33), ('bus003', 0, 0, 34), ('bus003', 0, 0, 35), ('bus003', 0, 0, 36),
  ('bus003', 0, 0, 37), ('bus003', 0, 0, 38), ('bus003', 0, 0, 39), ('bus003', 0, 0, 40),

  -- Seats for bus004
  ('bus004', 0, 0, 1), ('bus004', 0, 0, 2), ('bus004', 0, 0, 3), ('bus004', 0, 0, 4),
  ('bus004', 0, 0, 5), ('bus004', 0, 0, 6), ('bus004', 0, 0, 7), ('bus004', 0, 0, 8),
  ('bus004', 0, 0, 9), ('bus004', 0, 0, 10), ('bus004', 0, 0, 11), ('bus004', 0, 0, 12),
  ('bus004', 0, 0, 13), ('bus004', 0, 0, 14), ('bus004', 0, 0, 15), ('bus004', 0, 0, 16),
  ('bus004', 0, 0, 17), ('bus004', 0, 0, 18), ('bus004', 0, 0, 19), ('bus004', 0, 0, 20),
  ('bus004', 0, 0, 21), ('bus004', 0, 0, 22), ('bus004', 0, 0, 23), ('bus004', 0, 0, 24),
  ('bus004', 0, 0, 25), ('bus004', 0, 0, 26), ('bus004', 0, 0, 27), ('bus004', 0, 0, 28),
  ('bus004', 0, 0, 29), ('bus004', 0, 0, 30), ('bus004', 0, 0, 31), ('bus004', 0, 0, 32),
  ('bus004', 0, 0, 33), ('bus004', 0, 0, 34), ('bus004', 0, 0, 35), ('bus004', 0, 0, 36),
  ('bus004', 0, 0, 37), ('bus004', 0, 0, 38), ('bus004', 0, 0, 39), ('bus004', 0, 0, 40);
