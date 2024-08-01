create table if not exists users (
username varchar(50) not null primary key,
password varchar(500) not null,
enabled boolean not null);

create table if not exists authorities (
username varchar(50) not null,
authority varchar(50) not null,
constraint fk_authorities_users
foreign key(username) references users(username));

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


