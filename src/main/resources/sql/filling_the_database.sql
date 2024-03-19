use test_task_db;

INSERT INTO lectors (name, degree, salary) VALUES
('John Smith', 'assistant', 50000),
('Alice Johnson', 'associate professor', 60000),
('Michael Brown', 'professor', 70000),
('Emily Davis', 'assistant', 55000),
('Robert Wilson', 'associate professor', 65000),
('Jennifer Martinez', 'professor', 75000),
('William Anderson', 'assistant', 52000),
('Elizabeth Thomas', 'associate professor', 62000),
('David Taylor', 'professor', 72000),
('Sarah Lee', 'assistant', 53000),
('Sarah Hernandez', 'associate professor', 63000),
('Saraha Young', 'professor', 73000),
('Daniel Miller', 'assistant', 54000),
('Sophia White', 'associate professor', 64000),
('James Jackson', 'professor', 74000),
('Olivia Harris', 'assistant', 51000),
('Andrew Clark', 'associate professor', 61000),
('Victoria Lewis', 'professor', 71000),
('Charles King', 'assistant', 58000),
('Emma Scott', 'associate professor', 68000);

INSERT INTO departments (name, head_of_department_id) VALUES
('Department A', 1),
('Department B', 2),
('Department C', 3),
('Department D', 4),
('Department E', 5);

INSERT INTO department_lector (lector_id, department_id) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 1),
(7, 2),
(8, 3),
(9, 4),
(10, 5),
(11, 1),
(12, 2),
(13, 3),
(14, 4),
(15, 5),
(16, 1),
(17, 2),
(18, 3),
(19, 4),
(20, 5);