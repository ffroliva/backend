-- password = 123456
insert into users (id, username, first_name, last_name, email, password, created_at, updated_at) values (1, 'ffroliva', 'Flavio', 'Oliva', 'ffroliva@gmail.com', '$2a$10$m7nfNS9dnhQLKc155PfzXOYCrEX9u.wP23R.OsQ7EE.dRM/kHd4lq', '2019-06-19 12:00:00', '2019-06-19 12:00:00');
insert into role (id, name) values (1, 'ROLE_USER');
insert into role (id, name) values (2, 'ROLE_ADMIN');