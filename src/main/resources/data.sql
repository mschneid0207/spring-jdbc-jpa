/*create table person
(
  id integer not null,
  name varchar(255) not null,
  location varchar(255),
  birth_date timestamp,
  primary key (id)
);*/

insert into course (id, name, updated_date, created_date)
values (1001, 'JPA in 50 steps', sysdate(), sysdate());
insert into course (id, name, updated_date, created_date)
values (1002, 'Spring in 100 steps', sysdate(), sysdate());
insert into course (id, name, updated_date, created_date)
values (1003, 'Spring  Boot in 80 steps', sysdate(), sysdate());

insert into passport (id, number)
values (4001, 'E1234');
insert into passport (id, number)
values (4002, 'E3564');
insert into passport (id, number)
values (4003, 'E8337');
insert into passport (id, number)
values (4004, 'E9876');

insert into student (id, name, passport_id)
values (2001, 'Markus', 4001);
insert into student (id, name, passport_id)
values (2002, 'Nadine', 4002);
insert into student (id, name, passport_id)
values (2003, 'Lukas', 4003);
insert into student (id, name, passport_id)
values (2004, 'Julian', 4004);

insert into review (id, rating, description, course_id)
values (5001, '3', 'Great Course', 1001);
insert into review (id, rating, description, course_id)
values (5002, '4', 'Wonderful Course', 1001);
insert into review (id, rating, description, course_id)
values (5003, '5', 'Awesome Course', 1003);



insert into person
(id, name, location, birth_date)
values (1000, 'Markus Schneider', 'Unterdietfurt', sysdate());
insert into person
(id, name, location, birth_date)
values (1001, 'Max Mustermann', 'Frankfurt', sysdate());
insert into person
(id, name, location, birth_date)
values (1002, 'Lisa Musterfrau', 'Köln', sysdate());