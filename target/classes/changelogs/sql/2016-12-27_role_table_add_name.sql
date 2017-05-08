--liquibase formatted sql

--changeset me:role_table_add_name
ALTER TABLE role ADD name VARCHAR(50);
update role set name = 'Администратор' where id = 1;
update role set name = 'Начальник' where id = 3;
update role set name = 'Пользователь' where id = 2;
--rollback drop table role