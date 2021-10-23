--liquibase formatted sql

--changeset Abbad:0.1/1

CREATE SEQUENCE IF NOT EXISTS hibernate_sequence START 1000;
