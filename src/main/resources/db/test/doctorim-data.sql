--liquibase formatted sql

--changeset Abbad:0.1-data/1
CREATE SEQUENCE IF NOT EXISTS hibernate_sequence START WITH 1000;

--changeset Abbad:0.1-data/2

INSERT INTO USERS VALUES
(1,'ABBAD','12345','37603030' ),
(2,'ABBAD_TN','12345','+21623201318' ),
(3,'ABBAD_DR','12345','44603030' ),
(4,'BANKILY','12345','*888#' );

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
