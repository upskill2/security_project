insert ignore into users values ('user', '{noop}password', true);
insert ignore authorities values ('user', 'read');


insert ignore into users values ('admin', '{bcrypt}$2a$12$olTdFhEPhKSLUkKWmYKHCOPG7XHl9NX0Wb.v.twX5emkOffXE92KO', true);
insert ignore authorities values ('admin', 'admin');

insert ignore into customer (email,pwd,`role`,`create_dt`,`mobile_number`, `name`) values('@user.com', '{noop}password', 'read',CURDATE(),'5334122365','test');
insert ignore into customer (email,pwd,`role`,`create_dt`,`mobile_number`,`name`) values ('@admin.com', '{bcrypt}$2a$12$olTdFhEPhKSLUkKWmYKHCOPG7XHl9NX0Wb.v.twX5emkOffXE92KO', 'admin',CURDATE(),'5334122365', 'something_1');

insert into `accounts` (`customer_id`, `account_number`, `account_type`, `branch_address`, `create_dt`) VALUES (1, 1865764534, 'Savings', '123 Main Street,New York', CURDATE());

insert into `cards` (`card_number`, `customer_id`, `card_type`, `total_limit`, `amount_used`, `available_amount`, `create_dt`) VALUES ('4565XXXX4656', 1, 'Credit', 10000, 500, 9500, CURDATE());

insert into `cards` (`card_number`, `customer_id`, `card_type`, `total_limit`, `amount_used`, `available_amount`, `create_dt`) VALUES ('3455XXXX8673', 1, 'Credit', 7500, 600, 6900, CURDATE());

insert into `cards` (`card_number`, `customer_id`, `card_type`, `total_limit`, `amount_used`, `available_amount`, `create_dt`) VALUES ('2359XXXX9346', 1, 'Credit', 20000, 4000, 16000, CURDATE());

INSERT INTO `notice_details` ( `notice_summary`, `notice_details`, `notic_beg_dt`, `notic_end_dt`, `create_dt`, `update_dt`)
VALUES ('Home Loan Interest rates reduced', 'Home loan interest rates are reduced as per the goverment guidelines. The updated rates will be effective immediately',
CURDATE() - INTERVAL 30 DAY, CURDATE() + INTERVAL 30 DAY, CURDATE(), null);

INSERT INTO `notice_details` ( `notice_summary`, `notice_details`, `notic_beg_dt`, `notic_end_dt`, `create_dt`, `update_dt`)
VALUES ('Net Banking Offers', 'Customers who will opt for Internet banking while opening a saving account will get a $50 amazon voucher',
CURDATE() - INTERVAL 30 DAY, CURDATE() + INTERVAL 30 DAY, CURDATE(), null);

INSERT INTO `notice_details` ( `notice_summary`, `notice_details`, `notic_beg_dt`, `notic_end_dt`, `create_dt`, `update_dt`)
VALUES ('Mobile App Downtime', 'The mobile application of the EazyBank will be down from 2AM-5AM on 12/05/2020 due to maintenance activities',
CURDATE() - INTERVAL 30 DAY, CURDATE() + INTERVAL 30 DAY, CURDATE(), null);

INSERT INTO `notice_details` ( `notice_summary`, `notice_details`, `notic_beg_dt`, `notic_end_dt`, `create_dt`, `update_dt`)
VALUES ('E Auction notice', 'There will be a e-auction on 12/08/2020 on the Bank website for all the stubborn arrears.Interested parties can participate in the e-auction',
CURDATE() - INTERVAL 30 DAY, CURDATE() + INTERVAL 30 DAY, CURDATE(), null);

INSERT INTO `notice_details` ( `notice_summary`, `notice_details`, `notic_beg_dt`, `notic_end_dt`, `create_dt`, `update_dt`)
VALUES ('Launch of Millennia Cards', 'Millennia Credit Cards are launched for the premium customers of EazyBank. With these cards, you will get 5% cashback for each purchase',
CURDATE() - INTERVAL 30 DAY, CURDATE() + INTERVAL 30 DAY, CURDATE(), null);

INSERT INTO `notice_details` ( `notice_summary`, `notice_details`, `notic_beg_dt`, `notic_end_dt`, `create_dt`, `update_dt`)
VALUES ('COVID-19 Insurance', 'EazyBank launched an insurance policy which will cover COVID-19 expenses. Please reach out to the branch for more details',
CURDATE() - INTERVAL 30 DAY, CURDATE() + INTERVAL 30 DAY, CURDATE(), null);