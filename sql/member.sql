create table member(
    memberId char(6),
    id char(8) not null,
    name varchar(50) not null,
    primary key (memberId),
    constraint UC_Member unique (id)
);