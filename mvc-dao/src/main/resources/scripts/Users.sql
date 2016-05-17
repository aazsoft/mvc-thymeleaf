CREATE TABLE `mvc`.`user` (
  `id` FLOAT NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password_hash` VARCHAR(150) NOT NULL,
  `role` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC));
  
INSERT INTO `mvc`.`user` (`id`, `email`, `password_hash`, `role`) VALUES ('1', 'ad@gmail.com', '$2a$10$YFRa6Tlk9I4mmjSpDVRjwOS1wcWwXexxfQFBrDphusbTQK/966GZ6', 'ADMIN');
INSERT INTO `mvc`.`user` (`id`, `email`, `password_hash`, `role`) VALUES ('2', 't@gmail.com', '$2a$10$YFRa6Tlk9I4mmjSpDVRjwOS1wcWwXexxfQFBrDphusbTQK/966GZ6', 'USER');  
