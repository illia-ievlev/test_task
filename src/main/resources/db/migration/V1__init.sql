create table if not exists lectors
(
    id     bigint                                                 not null primary key auto_increment unique,
    name   varchar(50)                                            not null,
    degree enum ('assistant', 'associate professor', 'professor') not null,
    salary bigint                                                 not null
);

create table if not exists departments
(
    id                    bigint      not null primary key auto_increment unique,
    name                  varchar(50) not null unique,
    head_of_department_id bigint      not null,
    foreign key (head_of_department_id) references lectors (id)
);

create table if not exists department_lector
(
    lector_id     bigint not null,
    department_id bigint not null,
    primary key (lector_id, department_id),
    foreign key (lector_id) references lectors (id),
    foreign key (department_id) references departments (id)
);



