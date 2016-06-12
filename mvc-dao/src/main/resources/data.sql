INSERT INTO user (id, email, password_hash, age, username) VALUES 
(1, 'ad@gmail.com', '$2a$10$YFRa6Tlk9I4mmjSpDVRjwOS1wcWwXexxfQFBrDphusbTQK/966GZ6', 45, 'admin');
INSERT INTO user (id, email, password_hash, age, username) VALUES 
(2, 't@gmail.com', '$2a$10$YFRa6Tlk9I4mmjSpDVRjwOS1wcWwXexxfQFBrDphusbTQK/966GZ6', 30, 'truong'); 
INSERT INTO role (id, role_name, role_description) VALUES (1, 'ADMIN', 'adminstrator');
INSERT INTO role (id, role_name, role_description) VALUES (2, 'USER', 'user');
INSERT INTO role (id, role_name, role_description) VALUES (3, 'POWER_USER', 'power user');
INSERT INTO user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO user_role (user_id, role_id) VALUES (2, 2);
INSERT INTO user_role (user_id, role_id) VALUES (2, 3);