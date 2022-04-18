DROP SCHEMA IF EXISTS infyinterns_db;

CREATE SCHEMA infyinterns_db;
USE infyinterns_db;
   
create table mentor (
   mentor_id INT AUTO_INCREMENT,
   mentor_name VARCHAR(30) not null,
   projects_mentored INT not null,
   CONSTRAINT ps_mentor_id_pk PRIMARY KEY (mentor_id)
);

create table project (
   project_id INT AUTO_INCREMENT,   
   project_name VARCHAR(30) not null,
   idea_owner INT not null,
   release_date DATE not null,
   mentor_id INT REFERENCES Mentor,
   CONSTRAINT ps_project_id_pk PRIMARY KEY (project_id)
);

INSERT INTO Mentor VALUES(1000,'William',0);
INSERT INTO Mentor VALUES(1001,'John',1);
INSERT INTO Mentor VALUES(1002,'Warner',3);
INSERT INTO Mentor VALUES(1003,'James',0);
INSERT INTO Mentor VALUES(1004,'Allen',0); 
INSERT INTO Mentor VALUES(1005,'Peter',1); 
INSERT INTO Mentor VALUES(1006,'Smith',1); 
INSERT INTO Mentor VALUES(1007,'Chris',1);
INSERT INTO Mentor VALUES(1008,'Alice',2);
INSERT INTO Mentor VALUES(1009,'Bethany',2);


INSERT INTO Project VALUES(1,'Shoe Cart',10012,sysdate()+ interval 40 day,1009);
INSERT INTO Project VALUES(2,'One-Stop Shop App',10025,sysdate()+ interval 50 day,1008);
INSERT INTO Project VALUES(3,'Buy N Sell Portal',10025,sysdate()+ interval 30 day,1008);
INSERT INTO Project VALUES(4,'Dine Easy',10000,sysdate()+ interval 20 day,1006);
INSERT INTO Project VALUES(5,'Tutor Point',10012,sysdate()+ interval 25 day,1002);
INSERT INTO Project VALUES(6,'Elevate',10000,sysdate()+ interval 35 day,1008);
INSERT INTO Project VALUES(7,'Health App',10018,sysdate()+ interval 15 day,1002);
INSERT INTO Project VALUES(8,'Visitor Portal',10018,sysdate()+ interval 10 day,1002);
INSERT INTO Project VALUES(9,'Chatter Box',10038,sysdate()+ interval 5 day,1005);
INSERT INTO Project VALUES(10,'Quiz Yourself',10009,sysdate()+ interval 25 day,1001);
INSERT INTO Project VALUES(11,'Take a Ride',10038,sysdate()+ interval 10 day,1009);
INSERT INTO Project VALUES(12,'BookBay',10096,sysdate()+ interval 10 day,1007);

commit;

select * from mentor;

select * from project;