CREATE TABLE developers_skills
(
    developer_id integer NOT NULL,
    skill_id     integer NOT NULL,
    primary key (developer_id,skill_id),
    foreign key (developer_id) references developer(id),
    foreign key (skill_id) references skill(id)
);