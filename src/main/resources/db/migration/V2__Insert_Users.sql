INSERT INTO customer (name, surname, password, email, activation_code, birth_date, phone_number) VALUES ('Admin', 'Boss', 'qwwerty123', 'amdin@mail.com', '13jdhjrh4j5j6', '01-01-2003', '+380986329027');
INSERT INTO customer (name, surname, password, email, activation_code, birth_date, phone_number) VALUES ('User', 'Slave', '123qwerty', 'slave@mail.com', '13jdhjrh4sdfg6', '02-02-2003', '+380982028031');

INSERT INTO user_role (user_id, roles) VALUES (1, 'ADMIN');
INSERT INTO user_role (user_id, roles) VALUES (1, 'USER');
INSERT INTO user_role (user_id, roles) VALUES (2, 'USER');