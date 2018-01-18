/*CREATE TABLE `school`.`groups` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `school`.`student` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `age` INT(3) ZEROFILL NOT NULL,
  `groupId` int NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`groupId`) REFERENCES groups(`id`));*/

/*
CREATE
Вставить 4 группы с разными названиями
id	name
1	A1
2	B2
3	C3
4	В34

Вставить 6 студентов разных (имена произвольные)
id	name	age	groupId
1	ХХ		29	1
2	ХХ		20	1
3	ХХ		21	2
4	ХХ		22	2
5	ХХ		23	3
6	ХХ		27	3

SELECT
  Отобразить все группы
Отобразить имена студентов старше 21
Отобразить имена и возраст студентов которые учатся в группе А1 (какой-то там join, погуглить)

UPDATE
  переименовать группу В34 в D4
Перевести всех студентов которые учатся в группе А1 в группу С3


DELETE
  Удалить группу В34
Удалить студентов старше 25
*/

#INSERT INTO groups (name) VALUES ('A1'), ('B2'), ('C2'), ('B34');

#SELECT * FROM groups;

/*INSERT INTO student (name, age, groupId) VALUES ('Ivan', '29', '1'), ('Petya', '20', '1'),
  ('Alexander', '21', '2'), ('Sergey', '22', '2'), ('Misha', '23', '3'), ('Vasya', '27', '3');*/
#SELECT * FROM student;

/*SELECT student.name,student.age from student INNER JOIN groups g ON student.groupId = g.id
WHERE g.name = 'A1';*/
