CREATE TABLE `roles`
(
    `id`   int NOT NULL AUTO_INCREMENT,
    `name` varchar(100) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 32
  DEFAULT CHARSET = utf8;
CREATE TABLE `users`
(
    `id`         int NOT NULL AUTO_INCREMENT,
    `login`      varchar(45)  DEFAULT NULL,
    `password`   varchar(45)  DEFAULT NULL,
    `email`      varchar(100) DEFAULT NULL,
    `first_name` varchar(45)  DEFAULT NULL,
    `last_name`  varchar(45)  DEFAULT NULL,
    `birth_date` date         DEFAULT NULL,
    `role_id`    int          DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `role_id_idx` (`role_id`),
    CONSTRAINT `role_id` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE SET NULL
) ENGINE = InnoDB
  AUTO_INCREMENT = 79
  DEFAULT CHARSET = utf8;