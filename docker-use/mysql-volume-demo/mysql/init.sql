create database if not exists test ;
use test;

create TABLE if not EXISTS students(
id INT not null auto_increment ,
name varchar(20) ,
age int ,
`identity` varchar(18),
primary key (id)
)ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8
;
insert into students(name,age,`identity`)
VALUES('zs',18,'11111111');
insert into students(name,age,`identity`)
VALUES('ls',19,'222222222');
insert into students(name,age,`identity`)
VALUES('wwu',20,'333333333');
commit;