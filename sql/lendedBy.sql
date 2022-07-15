create table lendedBy(
    isbn char(13) not null,
    memberId char(6) not null,
    lendingDate date,
    primary key (isbn, memberId),
    foreign key (isbn) references lendingbook(isbn),
    foreign key (memberId) references member(memberId)
);