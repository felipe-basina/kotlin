CREATE DATABASE IF NOT EXISTS fakedb;

USE fakedb;

CREATE TABLE IF NOT EXISTS fakeone (
   id MEDIUMINT NOT NULL AUTO_INCREMENT,
   description varchar(255),
    code varchar(255),
    creation_date DATETIME,
    business_date DATE,
    active boolean,
    primary key (id)
);

SET character_set_client = utf8;
SET character_set_connection = utf8;
SET character_set_results = utf8;
SET collation_connection = utf8_general_ci;

INSERT INTO fakeone (id, description, code, creation_date, business_date, active) values
(1, "Generic description", "d5f3368a-303c-4fdb-835d-2fefeefc9362", now(), curdate(), false),
(2, "Another simple description about renting a car", "11911efc-8ed2-4df9-9223-80391e1cf305", now(), curdate(), true);