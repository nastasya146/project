--liquibase formatted sql

--changeset me:task_table_user_id
ALTER TABLE task ADD user_id INT;
--rollback drop table task