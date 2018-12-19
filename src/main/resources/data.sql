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


insert into person
(id, name, location, birth_date)
values (1000, 'Markus Schneider', 'Unterdietfurt', sysdate());
insert into person
(id, name, location, birth_date)
values (1001, 'Max Mustermann', 'Frankfurt', sysdate());
insert into person
(id, name, location, birth_date)
values (1002, 'Lisa Musterfrau', 'Köln', sysdate());