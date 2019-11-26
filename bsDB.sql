drop table message;
drop table contacts;
drop table user;

create table user (id varchar(8), username varchar(16), password varchar(16));

alter table user
add constraint user_username_pk primary key(username);

insert into user
values('123','brian','bpass');
insert into user
values('1234','job','jpass');

Select user;
