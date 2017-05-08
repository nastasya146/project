--liquibase formatted sql

--changeset me:create_role_table
create table role (
  id INT(6) AUTO_INCREMENT PRIMARY KEY,
  role VARCHAR(50)
);
insert into role (id, role) values (1, 'admin'), (2, 'user'), (3, 'director')
--rollback drop table role

--changeset me:user_table_add_role
ALTER TABLE user ADD role_id INT;
--rollback drop table user