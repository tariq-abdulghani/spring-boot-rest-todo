drop table if exists todos;

create table todos(
  id INT AUTO_INCREMENT  PRIMARY KEY,
  title VARCHAR(250) NOT NULL,
  description VARCHAR(250) NOT NULL,
  creation_date Date 
);

insert into todos(id, title, description, creation_date) values(1, 'todo1', 'todo1 desc', '2021-09-27');
insert into todos(id, title, description, creation_date) values(2, 'todo2', 'todo2 desc', '2021-09-27');
insert into todos(id, title, description, creation_date) values(3, 'todo3', 'todo3 desc', '2021-09-27');
insert into todos(id, title, description, creation_date) values(4, 'todo4', 'todo4 desc', '2021-09-27');
insert into todos(id, title, description, creation_date) values(5, 'todo5', 'todo5 desc', '2021-09-27');