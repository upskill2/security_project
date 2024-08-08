insert ignore into users values ('user', '{noop}password', true);
insert ignore authorities values ('user', 'read');


insert ignore into users values ('admin', '{bcrypt}$2a$12$olTdFhEPhKSLUkKWmYKHCOPG7XHl9NX0Wb.v.twX5emkOffXE92KO', true);
insert ignore authorities values ('admin', 'admin');

insert ignore into customer (email,pwd,`role`,`create_dt`,`mobile_number`, `name`) values('@user.com', '{noop}password', 'read',CURDATE(),'5334122365','test');
insert ignore into customer (email,pwd,`role`,`create_dt`,`mobile_number`,`name`) values ('@admin.com', '{bcrypt}$2a$12$olTdFhEPhKSLUkKWmYKHCOPG7XHl9NX0Wb.v.twX5emkOffXE92KO', 'admin',CURDATE(),'5334122365', 'something_1');

INSERT INTO `accounts` (`customer_id`, `account_number`, `account_type`, `branch_address`, `create_dt`) VALUES (1, 1865764534, 'Savings', '123 Main Street,New York', CURDATE());
