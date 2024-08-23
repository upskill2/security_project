--Default Spring Security tables

/*create table if not exists users (
username varchar(50) not null primary key,
password varchar(500) not null,
enabled boolean not null);*/

/*create table if not exists authorities (
username varchar(50) not null,
authority varchar(50) not null,
constraint fk_authorities_users
foreign key(username) references users(username));*/

drop table if exists accounts_account_transactions cascade;
drop table if exists account_transactions cascade;
drop table if exists accounts cascade;
drop table if exists loans cascade;
drop table if exists cards cascade;
drop table if exists notice_details cascade;
drop table if exists contact_messages cascade;
drop table if exists authorities cascade;
drop table if exists customer cascade;

CREATE TABLE if not exists customer (
  customer_id bigint NOT NULL AUTO_INCREMENT,
  name varchar(100) NOT NULL,
  email varchar(100) NOT NULL,
  mobile_number varchar(20) NOT NULL,
  pwd varchar(500) NOT NULL,
  role varchar(100) NOT NULL,
  create_dt date DEFAULT NULL,
  PRIMARY KEY (customer_id),
  unique (email));

CREATE TABLE if not exists `accounts` (
  `customer_id` bigint NOT NULL,
  `account_number` bigint NOT NULL,
  `account_type` varchar(100) NOT NULL,
  `branch_address` varchar(200) NOT NULL,
  `create_dt` date DEFAULT NULL,
  PRIMARY KEY (`account_number`),
  KEY `customer_id` (`customer_id`),
  CONSTRAINT `customer_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE CASCADE
);

CREATE TABLE `account_transactions` (
  `transaction_id` char(36) NOT NULL,
  `account_number` bigint NOT NULL,
  `customer_id` bigint NOT NULL,
  `transaction_dt` date NOT NULL,
  `transaction_summary` varchar(200) NOT NULL,
  `transaction_type` varchar(100) NOT NULL,
  `transaction_amt` double NOT NULL,
  `closing_balance` double NOT NULL,
  `create_dt` date DEFAULT NULL,
  PRIMARY KEY (`transaction_id`),
  KEY `customer_id` (`customer_id`),
  KEY `account_number` (`account_number`),
  CONSTRAINT `accounts_ibfk_2` FOREIGN KEY (`account_number`) REFERENCES `accounts` (`account_number`) ON DELETE CASCADE,
  CONSTRAINT `acct_user_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE CASCADE);

CREATE TABLE `loans` (
  `loan_number` int NOT NULL AUTO_INCREMENT,
  `customer_id` bigint NOT NULL,
  `start_dt` date NOT NULL,
  `loan_type` varchar(100) NOT NULL,
  `total_loan` int NOT NULL,
  `amount_paid` int NOT NULL,
  `outstanding_amount` int NOT NULL,
  `create_dt` date DEFAULT NULL,
  PRIMARY KEY (`loan_number`),
  KEY `customer_id` (`customer_id`),
  CONSTRAINT `loan_customer_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE CASCADE
);

CREATE TABLE if not exists `cards` (
  `card_id` int NOT NULL AUTO_INCREMENT,
  `card_number` varchar(100) NOT NULL,
  `customer_id` bigint NOT NULL,
  `card_type` varchar(100) NOT NULL,
  `total_limit` int NOT NULL,
  `amount_used` int NOT NULL,
  `available_amount` int NOT NULL,
  `create_dt` date DEFAULT NULL,
  PRIMARY KEY (`card_id`),
  KEY `customer_id` (`customer_id`),
  CONSTRAINT `card_customer_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE CASCADE);


CREATE TABLE if not exists `notice_details` (
  `notice_id` int NOT NULL AUTO_INCREMENT,
  `notice_summary` varchar(200) NOT NULL,
  `notice_details` varchar(500) NOT NULL,
  `notic_beg_dt` date NOT NULL,
  `notic_end_dt` date DEFAULT NULL,
  `create_dt` date DEFAULT NULL,
  `update_dt` date DEFAULT NULL,
  PRIMARY KEY (`notice_id`)
);

CREATE TABLE if not exists `contact_messages` (
  `contact_id` char(36) NOT NULL,
  `contact_name` varchar(50) NOT NULL,
  `contact_email` varchar(100) NOT NULL,
  `subject` varchar(500) NOT NULL,
  `message` varchar(2000) NOT NULL,
  `create_dt` date DEFAULT NULL,
  PRIMARY KEY (`contact_id`)
);

CREATE TABLE `authorities` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `customer_id` bigint NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `customer_id` (`customer_id`),
  CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`)
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


