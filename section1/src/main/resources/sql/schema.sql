create table if not exists users (
username varchar(50) not null primary key,
password varchar(500) not null,
enabled boolean not null);

create table if not exists authorities (
username varchar(50) not null,
authority varchar(50) not null,
constraint fk_authorities_users
foreign key(username) references users(username));

create TABLE if not exists `customer` (
  `customer_id` bigint AUTO_INCREMENT primary key NOT NULL,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `mobile_number` varchar(20) NOT NULL,
  `pwd` varchar(500) NOT NULL,
  `role` varchar(100) NOT NULL,
  `create_dt` date DEFAULT NULL);

CREATE TABLE if not exists `accounts` (
  `customer_id` bigint NOT NULL,
  `account_number` int NOT NULL,
  `account_type` varchar(100) NOT NULL,
  `branch_address` varchar(200) NOT NULL,
  `create_dt` date DEFAULT NULL,
  PRIMARY KEY (`account_number`),
  KEY `customer_id` (`customer_id`),
  CONSTRAINT `customer_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE CASCADE
);

--DROP PROCEDURE IF EXISTS `bankdb`.`CreateIndexIfNotExists` ^;

/*CREATE PROCEDURE CreateIndexIfNotExists()
BEGIN
    DECLARE indexExists INT;

    SELECT COUNT(1)
   INTO indexExists
    FROM information_schema.statistics
    WHERE table_schema = 'bankdb'
      AND table_name = 'authorities'
      AND index_name = 'ix_auth_username';

    IF indexExists = 0 THEN
        CREATE UNIQUE INDEX ix_auth_username ON authorities (username, authority);
    END IF;
END ;*/

--call CreateIndexIfNotExists;


