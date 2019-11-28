use mysql;
grant all privileges on * to student@localhost identified by 'hello' with grant option;
commit;
create database student_space;
grant all on student_space.* to student identified by 'hello';