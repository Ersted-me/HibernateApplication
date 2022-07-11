CREATE TABLE developer
(
    id           SERIAL,
    first_name   varchar(50) NOT NULL,
    last_name    varchar(50) NOT NULL,
    specialty_id integer     NOT NULL,
    primary key (id),
    foreign key (specialty_id) references specialty (id)
);