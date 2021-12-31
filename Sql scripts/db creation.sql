DROP SCHEMA IF EXISTS `mobile-number-portability`;

CREATE SCHEMA `mobile-number-portability`;

use `mobile-number-portability`;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `organization`;

CREATE TABLE `organization` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL UNIQUE,
  `start_range` varchar(45) DEFAULT NULL,
  `end_range` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(45) DEFAULT NULL UNIQUE,
  `password` varchar(45) DEFAULT NULL,
  `role` varchar(45) DEFAULT NULL,
  `organization_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`organization_id`) 
  REFERENCES `organization` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `request`;

CREATE TABLE `request` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phone_number` varchar(128) DEFAULT NULL,
  `request_date` DATE DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `from_organization_id` int(11) DEFAULT NULL,
  `to_organization_id` int(11) DEFAULT NULL,
  
  PRIMARY KEY (`id`),
  FOREIGN KEY (`from_organization_id`) 
  REFERENCES `organization` (`id`) ,
  FOREIGN KEY (`to_organization_id`) 
  REFERENCES `organization` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO organization (`id`,`name`,`start_range`,`end_range`)
VALUES (1,'Vodafone','1000000000','1099999999');
INSERT INTO organization (`id`,`name`,`start_range`,`end_range`)
VALUES(2,'Etisalat','1100000000','1199999999');
INSERT INTO organization (`id`,`name`,`start_range`,`end_range`)
VALUES(3,'Orange','1200000000','1299999999');

INSERT INTO `mobile-number-portability`.`user` (`id`, `user_name`, `password`, `role`, `organization_id`)
VALUES ('1', 'Mostafa', 'password', 'ADMIN', '1');
INSERT INTO `mobile-number-portability`.`user` (`id`, `user_name`, `password`, `role`, `organization_id`)
VALUES ('2', 'Ahmed', 'password', 'ADMIN', '2');
INSERT INTO `mobile-number-portability`.`user` (`id`, `user_name`, `password`, `role`, `organization_id`)
VALUES ('3', 'Mohamed', 'password', 'Admin', '3');