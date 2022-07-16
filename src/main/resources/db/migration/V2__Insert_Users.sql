INSERT INTO customer (name, surname, password, email, activation_code, birth_date, phone_number) VALUES ('Admin', 'Boss', '$2a$08$WRp8M1i1J3KeUhDu/ldBpOJsBWdeEn3Q6C5pLxU2emZ76w0MetPXS', 'kovalchuk.roman03@gmail.com', '13jdhjrh4j5j6', '01-01-2003', '+380986329027');
INSERT INTO customer (name, surname, password, email, activation_code, birth_date, phone_number) VALUES ('User', 'Slave', '$2a$08$LZTooxVDuc256LIefFC.D.PIv9igqHk6dYA6jMBHlGV1a4R3m3cOC', 'podhorhyi_2002@ukr.net', '13jdhjrh4sdfg6', '02-02-2003', '+380982028031');

INSERT INTO user_role (user_id, roles) VALUES (1, 'ADMIN');
INSERT INTO user_role (user_id, roles) VALUES (1, 'USER');
INSERT INTO user_role (user_id, roles) VALUES (2, 'USER');