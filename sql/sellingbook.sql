create table sellingBook(
    isbn char(13),
    title varchar(100) not null,
    copyNumber int check (copyNumber > 0),
    itemsSold int check (itemsSold >= 0 and itemsSold <= copyNumber),
    primary key (isbn),
    constraint UC_LendingBook unique (title)
);