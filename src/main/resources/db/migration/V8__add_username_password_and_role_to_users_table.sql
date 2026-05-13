alter table library_users add column username varchar(255) unique;
alter table library_users add column password varchar(255) unique;
alter table library_users add column role varchar(255);
