DROP TABLE IF EXISTS `users`; 
CREATE TABLE `users` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `login` VARCHAR(256) NOT NULL ,
  `full_name` VARCHAR(2048) NOT NULL DEFAULT '' ,
  `deleted_at` DATETIME NULL DEFAULT NULL ,
  `password` VARCHAR(256) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) );

DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(128) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`));

DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE `user_roles` (
  `user_id` BIGINT NOT NULL,
  `role_id` INT NOT NULL,
  INDEX `fk_role_role_idx` (`role_id` ASC),
  INDEX `fk_role_user_idx` (`user_id` ASC),
  CONSTRAINT `fk_role_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_role_role`
    FOREIGN KEY (`role_id`)
    REFERENCES `roles` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
