insert ignore into users values ('user', '{noop}password', true);
insert ignore authorities values ('user', 'read');


insert ignore into users values ('admin', '{bcrypt}$2a$12$olTdFhEPhKSLUkKWmYKHCOPG7XHl9NX0Wb.v.twX5emkOffXE92KO', true);
insert ignore authorities values ('admin', 'admin');