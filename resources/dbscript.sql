CREATE DATABASE `pao_tema`;
USE 'pao_tema';

CREATE TABLE `persons` (
                           `id` int(11) NOT NULL AUTO_INCREMENT,
                           `name` varchar(45) NOT NULL,
                           `surname` varchar(45) NOT NULL,
                           `age` int(11) NOT NULL,
                           `address` varchar(45) NOT NULL,
                           `phoneNo` varchar(45) NOT NULL,
                           PRIMARY KEY (`id`)
);

CREATE TABLE `doctors` (
                           `id` int(11) NOT NULL AUTO_INCREMENT,
                           `name` varchar(15) NOT NULL,
                           `surname` varchar(15) NOT NULL,
                           `age` int(11) NOT NULL,
                           `address` varchar(45) NOT NULL,
                           `phoneNo` varchar(15) NOT NULL,
                           `salary` double NOT NULL,
                           `specialization` varchar(20) NOT NULL,
                           PRIMARY KEY (`id`)
);

CREATE TABLE `patients` (
                            `id` int(11) NOT NULL AUTO_INCREMENT,
                            `name` varchar(15) NOT NULL,
                            `surname` varchar(15) NOT NULL,
                            `age` int(11) NOT NULL,
                            `address` varchar(45) NOT NULL,
                            `phoneNo` varchar(15) NOT NULL,
                            `condition` varchar(45) NOT NULL,
                            PRIMARY KEY (`id`)
);

CREATE TABLE `suppliers` (
                             `id` int(11) NOT NULL AUTO_INCREMENT,
                             `name` varchar(45) NOT NULL,
                             `location` varchar(45) NOT NULL,
                             PRIMARY KEY (`id`)
);

CREATE TABLE `prescriptions` (
                                 `id` int(11) NOT NULL AUTO_INCREMENT,
                                 `barcode` varchar(10) NOT NULL,
                                 `date` date DEFAULT NULL,
                                 PRIMARY KEY (`id`)
);

CREATE TABLE `medications` (
                               `id` int(11) NOT NULL AUTO_INCREMENT,
                               `name` varchar(45) NOT NULL,
                               `price` double NOT NULL,
                               `id_supplier` int(11) DEFAULT NULL,
                               PRIMARY KEY (`id`),
                               KEY `medications_ibfk_1` (`id_supplier`),
                               CONSTRAINT `medications_ibfk_1` FOREIGN KEY (`id_supplier`) REFERENCES `suppliers` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
);

CREATE TABLE `medtopresc` (
                              `id_medication` int(11) NOT NULL,
                              `id_prescription` int(11) NOT NULL,
                              PRIMARY KEY (`id_medication`,`id_prescription`),
                              KEY `medtopresc_ibfk_2` (`id_prescription`),
                              CONSTRAINT `medtopresc_ibfk_1` FOREIGN KEY (`id_medication`) REFERENCES `medications` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
                              CONSTRAINT `medtopresc_ibfk_2` FOREIGN KEY (`id_prescription`) REFERENCES `prescriptions` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `appointments` (
                                `id` int(11) NOT NULL AUTO_INCREMENT,
                                `id_patient` int(11) NOT NULL,
                                `id_doctor` int(11) NOT NULL,
                                `id_prescription` int(11) DEFAULT NULL,
                                `date` date NOT NULL,
                                PRIMARY KEY (`id`),
                                KEY `appointments_ibfk_1` (`id_patient`),
                                KEY `appointments_ibfk_2` (`id_doctor`),
                                KEY `appointments_ibfk_3` (`id_prescription`),
                                CONSTRAINT `appointments_ibfk_1` FOREIGN KEY (`id_patient`) REFERENCES `patients` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
                                CONSTRAINT `appointments_ibfk_2` FOREIGN KEY (`id_doctor`) REFERENCES `doctors` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
                                CONSTRAINT `appointments_ibfk_3` FOREIGN KEY (`id_prescription`) REFERENCES `prescriptions` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
);




