--liquibase formatted sql

--changeset me:create_status_table
create table status (
  id INT(6) AUTO_INCREMENT PRIMARY KEY,
  status VARCHAR(50)
);
insert into status (id, status) values (1, 'Создание'), (2, 'Выполняется'), (3, 'Проверка'), (4, 'Завершена')
--rollback drop table status

--changeset me:task_table_add_status
ALTER TABLE task ADD status_id INT;
--rollback drop table task

--changeset me:task_table_drop_email
ALTER TABLE task drop email;
--rollback drop table task