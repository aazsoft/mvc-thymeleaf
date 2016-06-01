CREATE TABLE `mvc`.`user` (
  `id` FLOAT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NOT NULL,
  `password_hash` VARCHAR(150) NOT NULL,
  `role` VARCHAR(10) NOT NULL,
  `age` INT not null,
  `username` VARCHAR(45) NOT NULL UNIQUE,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC));
  
INSERT INTO `mvc`.`user` (`id`, `email`, `password_hash`, `role`, `age`, `username`) VALUES 
('1', 'ad@gmail.com', '$2a$10$YFRa6Tlk9I4mmjSpDVRjwOS1wcWwXexxfQFBrDphusbTQK/966GZ6', 'ADMIN', 45, 'admin');
INSERT INTO `mvc`.`user` (`id`, `email`, `password_hash`, `role`, `age`, `username`) VALUES 
('2', 't@gmail.com', '$2a$10$YFRa6Tlk9I4mmjSpDVRjwOS1wcWwXexxfQFBrDphusbTQK/966GZ6', 'USER', 30, 'truong');  
