CREATE TABLE `mvc`.`user` (
  `id` FLOAT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NOT NULL,
  `password_hash` VARCHAR(150) NOT NULL,
  `age` INT not null,
  `username` VARCHAR(45) NOT NULL UNIQUE,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC));

CREATE TABLE `mvc`.`role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) NOT NULL,
  `role_description` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_name_UNIQUE` (`role_name`)
);


CREATE TABLE `mvc`.`user_role` (
  `user_id` float NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FK_role_id_idx` (`role_id`),
  CONSTRAINT `FK_role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);

  
  
INSERT INTO `mvc`.`user` (`id`, `email`, `password_hash`, `age`, `username`) VALUES 
('1', 'ad@gmail.com', '$2a$10$YFRa6Tlk9I4mmjSpDVRjwOS1wcWwXexxfQFBrDphusbTQK/966GZ6', 45, 'admin');
INSERT INTO `mvc`.`user` (`id`, `email`, `password_hash`, `age`, `username`) VALUES 
('2', 't@gmail.com', '$2a$10$YFRa6Tlk9I4mmjSpDVRjwOS1wcWwXexxfQFBrDphusbTQK/966GZ6', 30, 'truong'); 
commit; 

INSERT INTO `mvc`.`role` (`id`, `role_name`, `role_description`) VALUES ('1', 'ADMIN', 'adminstrator');
INSERT INTO `mvc`.`role` (`id`, `role_name`, `role_description`) VALUES ('2', 'USER', 'user');
INSERT INTO `mvc`.`role` (`id`, `role_name`, `role_description`) VALUES ('3', 'POWER_USER', 'power user');
commit;
INSERT INTO `mvc`.`user_role` (`user_id`, `role_id`) VALUES ('1', '1');
INSERT INTO `mvc`.`user_role` (`user_id`, `role_id`) VALUES ('2', '2');
INSERT INTO `mvc`.`user_role` (`user_id`, `role_id`) VALUES ('2', '3');
commit;

