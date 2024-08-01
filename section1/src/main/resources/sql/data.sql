insert ignore into users values ('user', '{noop}password', true);
insert ignore authorities values ('user', 'read');


insert ignore into users values ('admin', '{bcrypt}$2a$12$olTdFhEPhKSLUkKWmYKHCOPG7XHl9NX0Wb.v.twX5emkOffXE92KO', true);
insert ignore authorities values ('admin', 'admin');

insert ignore into customer (email,pwd,`role`) values ('@user.com', '{noop}password', 'read');
insert ignore into customer (email,pwd,`role`) values ('@admin.com', '{bcrypt}$2a$12$olTdFhEPhKSLUkKWmYKHCOPG7XHl9NX0Wb.v.twX5emkOffXE92KO', 'admin');