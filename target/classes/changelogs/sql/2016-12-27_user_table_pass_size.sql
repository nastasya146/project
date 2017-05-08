--liquibase formatted sql

--changeset me:user_table_set_login
alter table user modify password varchar(100)
--rollback drop table user