#Student Enrollment Database
#RAIK184H
#Spring 2016

use icoleman;

drop table if exists Email;
drop table if exists Enrollment;
drop table if exists Student;
drop table if exists Course;

create table Student (
	studentId int not null primary key auto_increment,
    firstName varchar(255) not null,
    lastName varchar(255) not null,
    nuid varchar(8) not null,
    constraint uniqueNUID unique index(nuid)
)engine=InnoDB,collate=latin1_general_cs;

create table Email (
	emailId int not null primary key auto_increment,
    address varchar(255) not null,
    studentId int not null,
    foreign key (studentId) references Student(studentId)
)engine=InnoDB,collate=latin1_general_cs;

create table Course (
	courseId int not null primary key auto_increment,
    name varchar(255) not null,
    description text
)engine=InnoDB,collate=latin1_general_cs;

create table Enrollment (
	enrollmentId int not null primary key auto_increment,
    term int not null,
    studentId int not null,
    courseId int not null,
    foreign key (studentId) references Student(studentId),
    foreign key (courseId) references Course(courseId),
    constraint uniqueCombination unique (term, studentId, courseId)
)engine=InnoDB,collate=latin1_general_cs;

insert into Student (studentId, firstName, lastName, nuid) values (1, 'Iami', 'Coleman', 07798185);
insert into Student (studentId, firstName, lastName, nuid) values (2, 'Nisha', 'Rao', 383924829);

insert into Email (emailId, address, studentId) values (1, 'yeetzchak@gmail.com', (SELECT studentId FROM Student WHERE firstName = 'Iami'));
insert into Email (emailId, address, studentId) values (2, 'icoleman@huskers.unl.edu', (SELECT studentId FROM Student WHERE firstName = 'Iami'));

insert into Course (courseId, name, description) values (1, 'RAIK184H', 'A software engineering and discrete math course.');
insert into Course (courseId, name, description) values (2, 'RAIK188H', 'An unending hell from which no light, or joy, escapes.');

insert into Enrollment (enrollmentId, term, studentId, courseId) values (1, 2016, (SELECT studentId FROM Student WHERE firstName = 'Iami'), (SELECT courseId FROM Course WHERE name = 'RAIK184H'));
insert into Enrollment (enrollmentId, term, studentId, courseId) values (2, 2016, (SELECT studentId FROM Student WHERE firstName = 'Nisha'), (SELECT courseId FROM Course WHERE name = 'RAIK184H'));

select * from Student s
	join Email e on s.studentId = e.studentId
	left join Enrollment en on e.studentId = s.studentId
	left join Course c on c.courseId = en.courseId;