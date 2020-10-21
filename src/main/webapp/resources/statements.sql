CREATE DATABASE `study_project`;

CREATE TABLE `study_project`.`roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

CREATE TABLE `study_project`.`users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `login` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `role_id_idx` (`role_id`),
  CONSTRAINT `role_id` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;

INSERT INTO study_project.roles (name) VALUES ('Admin');
INSERT INTO study_project.roles (name) VALUES ('User');

INSERT INTO study_project.users (login, password, email, first_name, last_name, birth_date, role_id)
VALUES ('fedor', 'root', 'fedorkhodko@gmail.com', 'Fedor', 'Khodko', '1989-10-16', 1);

INSERT INTO study_project.users (login, password, email, first_name, last_name, birth_date, role_id)
VALUES ('ivan', 'root', 'ivan@gmail.com', 'Ivan', 'Lykhoshers', '1987-09-02', 1);