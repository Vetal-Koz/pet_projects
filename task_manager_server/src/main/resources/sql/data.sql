insert into projects values (default, 'task_manager');

INSERT INTO users (email, password, role, username)
VALUES ('user1@example.com', 'password123', 'ROLE_USER', 'user1');

INSERT INTO users (email, password, role, username)
VALUES ('user2@example.com', 'password456', 'ROLE_USER', 'user2');

INSERT INTO users (email, password, role, username)
VALUES ('user3@example.com', 'password456', 'ROLE_USER', 'user3');

INSERT INTO users (email, password, role, username)
VALUES ('user4@example.com', 'password456', 'ROLE_USER', 'user4');

insert into proj_user values
(1, 1),
(1, 2),
(1, 3),
(1, 4);

INSERT INTO tasks (accomplish_to, created_at, project_id, user_id, description, task_type, title)
VALUES ('2024-07-01 10:00:00', '2024-06-23 10:00:00', 1, 1, 'Task description 1', 'TO_DO', 'Task 1');

INSERT INTO tasks (accomplish_to, created_at, project_id, user_id, description, task_type, title)
VALUES ('2024-07-02 11:00:00', '2024-06-23 11:00:00', 1, 2, 'Task description 2', 'IN_PROGRESS', 'Task 2');

INSERT INTO tasks (accomplish_to, created_at, project_id, user_id, description, task_type, title)
VALUES ('2024-07-03 12:00:00', '2024-06-23 12:00:00', 1, 1, 'Task description 3', 'TEST', 'Task 3');

INSERT INTO tasks (accomplish_to, created_at, project_id, user_id, description, task_type, title)
VALUES ('2024-07-04 13:00:00', '2024-06-23 13:00:00', 1, 2, 'Task description 4', 'DONE', 'Task 4');

