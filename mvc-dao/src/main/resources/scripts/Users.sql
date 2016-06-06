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

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) NOT NULL,
  `role_description` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_name_UNIQUE` (`role_name`)
);


CREATE TABLE `mvc`.`user_role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` FLOAT NOT NULL,
  `role_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_user_id_idx` (`user_id` ASC),
  INDEX `FK_role_id_idx` (`role_id` ASC),
  CONSTRAINT `FK_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `mvc`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_role_id`
    FOREIGN KEY (`role_id`)
    REFERENCES `mvc`.`role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


CREATE TABLE `mvc`.`menu_item` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `mi_name` VARCHAR(150) NOT NULL,
  `mi_parent_id` INT NULL,
  `mi_desc` VARCHAR(200) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC));

CREATE TABLE `mvc`.`permission` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `permission_name` VARCHAR(50) NOT NULL,
  `permission_desc` VARCHAR(150) NULL,
  `role_id` INT NOT NULL,
  `menuitem_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_per_role_id_idx` (`role_id` ASC),
  INDEX `FK_per_mi_id_idx` (`menuitem_id` ASC),
  CONSTRAINT `FK_per_role_id`
    FOREIGN KEY (`role_id`)
    REFERENCES `mvc`.`role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_per_mi_id`
    FOREIGN KEY (`menuitem_id`)
    REFERENCES `mvc`.`menu_item` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
  
  
INSERT INTO `mvc`.`user` (`id`, `email`, `password_hash`, `role`, `age`, `username`) VALUES 
('1', 'ad@gmail.com', '$2a$10$YFRa6Tlk9I4mmjSpDVRjwOS1wcWwXexxfQFBrDphusbTQK/966GZ6', 'ADMIN', 45, 'admin');
INSERT INTO `mvc`.`user` (`id`, `email`, `password_hash`, `role`, `age`, `username`) VALUES 
('2', 't@gmail.com', '$2a$10$YFRa6Tlk9I4mmjSpDVRjwOS1wcWwXexxfQFBrDphusbTQK/966GZ6', 'USER', 30, 'truong');  

INSERT INTO `mvc`.`role` (`id`, `role_name`, `role_description`) VALUES ('1', 'ADMIN', 'adminstrator');
INSERT INTO `mvc`.`role` (`id`, `role_name`, `role_description`) VALUES ('2', 'USER', 'user');
INSERT INTO `mvc`.`role` (`id`, `role_name`, `role_description`) VALUES ('3', 'POWER_USER', 'power user');

INSERT INTO `mvc`.`user_role` (`id`, `user_id`, `role_id`) VALUES ('1', '1', '1');
INSERT INTO `mvc`.`user_role` (`id`, `user_id`, `role_id`) VALUES ('2', '2', '2');
INSERT INTO `mvc`.`user_role` (`id`, `user_id`, `role_id`) VALUES ('3', '3', '2');
INSERT INTO `mvc`.`user_role` (`id`, `user_id`, `role_id`) VALUES ('4', '4', '2');
INSERT INTO `mvc`.`user_role` (`id`, `user_id`, `role_id`) VALUES ('5', '5', '2');

INSERT INTO `mvc`.`menu_item` (`id`, `mi_name`, `mi_desc`) VALUES ('1', 'Admin', 'admin menu');
INSERT INTO `mvc`.`menu_item` (`id`, `mi_name`, `mi_parent_id`, `mi_desc`) VALUES ('2', 'Create new user', '1', 'to create a new user');
INSERT INTO `mvc`.`menu_item` (`id`, `mi_name`, `mi_parent_id`, `mi_desc`) VALUES ('3', 'View all users', '1', 'to view all existing users');
INSERT INTO `mvc`.`menu_item` (`id`, `mi_name`, `mi_parent_id`, `mi_desc`) VALUES ('4', 'Search users', '1', 'to search existing users');
INSERT INTO `mvc`.`menu_item` (`id`, `mi_name`, `mi_parent_id`, `mi_desc`) VALUES ('5', 'View myself', '1', 'to view myself info');

INSERT INTO `mvc`.`permission` (`id`, `permission_name`, `role_id`, `menuitem_id`) VALUES ('1', 'admin_admin', '1', '1');
INSERT INTO `mvc`.`permission` (`id`, `permission_name`, `role_id`, `menuitem_id`) VALUES ('2', 'admin_new_user', '1', '2');
INSERT INTO `mvc`.`permission` (`id`, `permission_name`, `role_id`, `menuitem_id`) VALUES ('3', 'admin_users', '1', '3');
INSERT INTO `mvc`.`permission` (`id`, `permission_name`, `role_id`, `menuitem_id`) VALUES ('4', 'admin_search_user', '1', '4');
INSERT INTO `mvc`.`permission` (`id`, `permission_name`, `role_id`, `menuitem_id`) VALUES ('5', 'admin_self', '1', '5');
INSERT INTO `mvc`.`permission` (`id`, `permission_name`, `role_id`, `menuitem_id`) VALUES ('6', 'user_admin', '2', '1');
INSERT INTO `mvc`.`permission` (`id`, `permission_name`, `role_id`, `menuitem_id`) VALUES ('7', 'user_self', '2', '5');

