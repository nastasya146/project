--liquibase formatted sql

--changeset me:create_task_table
create table task (
  id INT(6) AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50),
  comment VARCHAR(50),
  creation_date TIMESTAMP,
  start_date TIMESTAMP,
  finish_date TIMESTAMP,
  email VARCHAR(50)
)
--rollback drop table task