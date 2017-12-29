#Create initial schema
CREATE DATABASE IF NOT EXISTS `ancor` DEFAULT CHARACTER SET utf8;
USE `ancor`;

#Clean start
DROP TABLE IF EXISTS `vessel_position`;
DROP TABLE IF EXISTS `vessel_position_bad_rows`;
DROP TABLE IF EXISTS `vessel`;
DROP TABLE IF EXISTS `port`;

#Structure for lookup-table ancor.vessel
CREATE TABLE IF NOT EXISTS `vessel` (
  `id` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `imo` VARCHAR(200) NOT NULL UNIQUE,
  `vessel_name` VARCHAR(200) NOT NULL UNIQUE,
  `gross_tonnage` INTEGER NOT NULL,

  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#Structure for lookup-table ancor.port
CREATE TABLE IF NOT EXISTS `port` (
  `id` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `from_port` VARCHAR(200) NOT NULL UNIQUE,
  `from_country` VARCHAR(200) NOT NULL UNIQUE,

  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#Structure for table ancor.vessel_position
CREATE TABLE IF NOT EXISTS `vessel_position` (
  `id` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `latitude` DOUBLE NOT NULL,
  `longitude` DOUBLE NOT NULL,
  `position_received_timestamp` TIMESTAMP NOT NULL,
  `speed` DOUBLE NOT NULL,
  `draught` DOUBLE NOT NULL,
  `year_built` INTEGER NOT NULL,
  `direction_degrees` INTEGER NOT NULL,
  `destination_port` VARCHAR(200) NOT NULL,
  `destination_country` VARCHAR(200) NOT NULL,
  `vessel_id` INTEGER UNSIGNED NOT NULL,
  `port_id` INTEGER UNSIGNED NOT NULL,

  INDEX (vessel_id),
  INDEX (port_id),

  PRIMARY KEY (`id`),

  FOREIGN KEY (`vessel_id`)
  REFERENCES vessel(`id`)
    ON UPDATE CASCADE ON DELETE RESTRICT,

  FOREIGN KEY (`port_id`)
  REFERENCES port(`id`)
    ON UPDATE CASCADE ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#Structure for table ancor.vessel_position
CREATE TABLE IF NOT EXISTS `vessel_position_bad_rows` (
  `id` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `course` INTEGER NULL,
  `wind` INTEGER NULL,
  `temperature` INTEGER NULL,
  `wind_direction` VARCHAR(200) NULL,

  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;