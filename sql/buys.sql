create table buys(
    id int AUTO_INCREMENT,
    isbn char(13) not null,
    memberId char(6) not null,
    sellingDate date,
    primary key (id),
    foreign key (isbn) references sellingbook(isbn),
    foreign key (memberId) references member(memberId)
);