--liquibase formatted sql

--changeset Abbad:0.1-data/1
CREATE SEQUENCE IF NOT EXISTS hibernate_sequence START WITH 1000;

--changeset Abbad:0.1-data/2

INSERT INTO USERS VALUES
(1,'37603030','37603030','$2a$10$6i1xacQlSzfdXGLFiJ0BpeNsXztO19HAYny/r1BXiu9Ra03nx3NFa','MED','ABBAD' ),
(2,'ABBAD_TN','+21623201318','$2a$10$6i1xacQlSzfdXGLFiJ0BpeNsXztO19HAYny/r1BXiu9Ra03nx3NFa','MED','ABBAD'),
(3,'ABBAD_DR','44603030','$2a$10$6i1xacQlSzfdXGLFiJ0BpeNsXztO19HAYny/r1BXiu9Ra03nx3NFa','MED','ABBAD' ),
(4,'BANKILY','*888#','$2a$10$6i1xacQlSzfdXGLFiJ0BpeNsXztO19HAYny/r1BXiu9Ra03nx3NFa',null,null );

INSERT INTO ROLES VALUES
(5,'ADMIN'),
(6,'DOCTOR'),
(7,'PATIENT');

INSERT INTO USERS_ROLES VALUES
(1,5),
(2,5),
(1,6),
(3,6),
(1,7),
(4,7);
