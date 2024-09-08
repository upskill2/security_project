--Default Spring Security tables

--insert ignore into users values ('user', '{noop}password', true);
--insert ignore authorities values ('user', 'read');

----------------------------------------------
--insert ignore into users values ('admin', '{bcrypt}$2a$12$olTdFhEPhKSLUkKWmYKHCOPG7XHl9NX0Wb.v.twX5emkOffXE92KO', true);
--insert ignore authorities values ('admin', 'admin');

----------------------------------------------
insert ignore into bankdb.customer (email,pwd,`role`,`create_dt`,`mobile_number`,`name`) values ('1@admin.com','{bcrypt}$2a$12$23vIWGWLiSZoT4KJABXEF.cf.CxprtOhlNtlSs0yRrNHiLYg8j8GO', 'ADMIN',CURDATE(),'5334122365', 'something_1');
insert ignore into bankdb.customer (email,pwd,`role`,`create_dt`,`mobile_number`, `name`) values('1@user.com', '{noop}password', 'USER',CURDATE(),'5334122365','test');

------------------------------------------
insert into `bankdb`.`accounts` (`customer_id`, `account_number`, `account_type`, `branch_address`, `create_dt`) VALUES (1, 1865764534, 'Savings', '123 Main Street,New York', CURDATE());

------------------------------------------------
insert into `bankdb`.`cards` (`card_number`, `customer_id`, `card_type`, `total_limit`, `amount_used`, `available_amount`, `create_dt`) VALUES ('4565XXXX4656', 1, 'Credit', 10000, 500, 9500, CURDATE());

insert into `bankdb`.`cards` (`card_number`, `customer_id`, `card_type`, `total_limit`, `amount_used`, `available_amount`, `create_dt`) VALUES ('3455XXXX8673', 1, 'Credit', 7500, 600, 6900, CURDATE());

insert into `bankdb`.`cards` (`card_number`, `customer_id`, `card_type`, `total_limit`, `amount_used`, `available_amount`, `create_dt`) VALUES ('2359XXXX9346', 1, 'Credit', 20000, 4000, 16000, CURDATE());

------------------------------------------------
INSERT INTO `bankdb`.`loans` ( `customer_id`, `start_dt`, `loan_type`, `total_loan`, `amount_paid`, `outstanding_amount`, `create_dt`)
 VALUES ( 1, '2020-10-13', 'Home', 200000, 50000, 150000, '2020-10-13');

INSERT INTO `bankdb`.`loans` ( `customer_id`, `start_dt`, `loan_type`, `total_loan`, `amount_paid`, `outstanding_amount`, `create_dt`)
 VALUES ( 1, '2020-06-06', 'Vehicle', 40000, 10000, 30000, '2020-06-06');

INSERT INTO `bankdb`.`loans` ( `customer_id`, `start_dt`, `loan_type`, `total_loan`, `amount_paid`, `outstanding_amount`, `create_dt`)
 VALUES ( 1, '2018-02-14', 'Home', 50000, 10000, 40000, '2018-02-14');

INSERT INTO `bankdb`.`loans` ( `customer_id`, `start_dt`, `loan_type`, `total_loan`, `amount_paid`, `outstanding_amount`, `create_dt`)
 VALUES ( 1, '2018-02-14', 'Personal', 10000, 3500, 6500, '2018-02-14');

-------------------------------------------------------
INSERT INTO `bankdb`.`notice_details` ( `notice_summary`, `notice_details`, `notic_beg_dt`, `notic_end_dt`, `create_dt`, `update_dt`)
VALUES ('Home Loan Interest rates reduced', 'Home loan interest rates are reduced as per the goverment guidelines. The updated rates will be effective immediately',
CURDATE() - INTERVAL 30 DAY, CURDATE() + INTERVAL 30 DAY, CURDATE(), null);

INSERT INTO `bankdb`.`notice_details` ( `notice_summary`, `notice_details`, `notic_beg_dt`, `notic_end_dt`, `create_dt`, `update_dt`)
VALUES ('Net Banking Offers', 'Customers who will opt for Internet banking while opening a saving account will get a $50 amazon voucher',
CURDATE() - INTERVAL 30 DAY, CURDATE() + INTERVAL 30 DAY, CURDATE(), null);

INSERT INTO `bankdb`.`notice_details` ( `notice_summary`, `notice_details`, `notic_beg_dt`, `notic_end_dt`, `create_dt`, `update_dt`)
VALUES ('Mobile App Downtime', 'The mobile application of the EazyBank will be down from 2AM-5AM on 12/05/2020 due to maintenance activities',
CURDATE() - INTERVAL 30 DAY, CURDATE() + INTERVAL 30 DAY, CURDATE(), null);

INSERT INTO `bankdb`.`notice_details` ( `notice_summary`, `notice_details`, `notic_beg_dt`, `notic_end_dt`, `create_dt`, `update_dt`)
VALUES ('E Auction notice', 'There will be a e-auction on 12/08/2020 on the Bank website for all the stubborn arrears.Interested parties can participate in the e-auction',
CURDATE() - INTERVAL 30 DAY, CURDATE() + INTERVAL 30 DAY, CURDATE(), null);

INSERT INTO `bankdb`.`notice_details` ( `notice_summary`, `notice_details`, `notic_beg_dt`, `notic_end_dt`, `create_dt`, `update_dt`)
VALUES ('Launch of Millennia Cards', 'Millennia Credit Cards are launched for the premium customers of EazyBank. With these cards, you will get 5% cashback for each purchase',
CURDATE() - INTERVAL 30 DAY, CURDATE() + INTERVAL 30 DAY, CURDATE(), null);

INSERT INTO `bankdb`.`notice_details` ( `notice_summary`, `notice_details`, `notic_beg_dt`, `notic_end_dt`, `create_dt`, `update_dt`)
VALUES ('COVID-19 Insurance', 'EazyBank launched an insurance policy which will cover COVID-19 expenses. Please reach out to the branch for more details',
CURDATE() - INTERVAL 30 DAY, CURDATE() + INTERVAL 30 DAY, CURDATE(), null);

-------------------------------------------------------
INSERT INTO `bankdb`.`account_transactions` (`transaction_id`, `account_number`, `customer_id`, `transaction_dt`, `transaction_summary`, `transaction_type`,`transaction_amt`,
`closing_balance`, `create_dt`)  VALUES (UUID(), 1865764534, 1, DATE_SUB(CURDATE(), INTERVAL 7 DAY), 'Coffee Shop', 'Withdrawal', 30,34500,DATE_SUB(CURDATE(), INTERVAL 7 DAY));

INSERT INTO `bankdb`.`account_transactions` (`transaction_id`, `account_number`, `customer_id`, `transaction_dt`, `transaction_summary`, `transaction_type`,`transaction_amt`,
`closing_balance`, `create_dt`)  VALUES (UUID(), 1865764534, 1, DATE_SUB(CURDATE(), INTERVAL 6 DAY), 'Uber', 'Withdrawal', 100,34400,DATE_SUB(CURDATE(), INTERVAL 6 DAY));

INSERT INTO `bankdb`.`account_transactions` (`transaction_id`, `account_number`, `customer_id`, `transaction_dt`, `transaction_summary`, `transaction_type`,`transaction_amt`,
`closing_balance`, `create_dt`)  VALUES (UUID(), 1865764534, 1, DATE_SUB(CURDATE(), INTERVAL 5 DAY), 'Self Deposit', 'Deposit', 500,34900,DATE_SUB(CURDATE(), INTERVAL 5 DAY));

INSERT INTO `bankdb`.`account_transactions` (`transaction_id`, `account_number`, `customer_id`, `transaction_dt`, `transaction_summary`, `transaction_type`,`transaction_amt`,
`closing_balance`, `create_dt`)  VALUES (UUID(), 1865764534, 1, DATE_SUB(CURDATE(), INTERVAL 4 DAY), 'Ebay', 'Withdrawal', 600,34300,DATE_SUB(CURDATE(), INTERVAL 4 DAY));

INSERT INTO `bankdb`.`account_transactions` (`transaction_id`, `account_number`, `customer_id`, `transaction_dt`, `transaction_summary`, `transaction_type`,`transaction_amt`,
`closing_balance`, `create_dt`)  VALUES (UUID(), 1865764534, 1, DATE_SUB(CURDATE(), INTERVAL 2 DAY), 'OnlineTransfer', 'Deposit', 700,35000,DATE_SUB(CURDATE(), INTERVAL 2 DAY));

INSERT INTO `bankdb`.`account_transactions` (`transaction_id`, `account_number`, `customer_id`, `transaction_dt`, `transaction_summary`, `transaction_type`,`transaction_amt`,
`closing_balance`, `create_dt`)  VALUES (UUID(), 1865764534, 1, DATE_SUB(CURDATE(), INTERVAL 1 DAY), 'Amazon.com', 'Withdrawal', 100,34900,DATE_SUB(CURDATE(), INTERVAL 1 DAY));
-------------------------------------------------------
INSERT INTO `bankdb`.`contact_messages` (`contact_id`, `contact_name`, `contact_email`, `subject`, `message`, `create_dt`) VALUES(UUID(), 'some', '1@user.com',
'XCV-0', 'test message', DATE_SUB(CURDATE(), INTERVAL 5 DAY));

-------------------------------------------------------
INSERT INTO `bankdb`.`authorities` (`customer_id`, `name`) VALUES (1, 'VIEWACCOUNT');

INSERT INTO `bankdb`.`authorities` (`customer_id`, `name`) VALUES (1, 'VIEWCARDS');

INSERT INTO `bankdb`.`authorities` (`customer_id`, `name`)  VALUES (1, 'VIEWLOANS');

INSERT INTO `bankdb`.`authorities` (`customer_id`, `name`)   VALUES (1, 'VIEWBALANCE');

--DELETE FROM `authorities`;

INSERT INTO `bankdb`.`authorities` (`customer_id`, `name`)  VALUES (1, 'USER');

INSERT INTO `bankdb`.`authorities` (`customer_id`, `name`)  VALUES (1, 'ADMIN');