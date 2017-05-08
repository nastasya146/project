--liquibase formatted sql

--changeset istomin:create_user_table
create table user (
  id INT(6) AUTO_INCREMENT PRIMARY KEY,
  login VARCHAR(50),
  password VARCHAR(50),
  reg_date TIMESTAMP,
  email VARCHAR(50)
)
--rollback drop table user