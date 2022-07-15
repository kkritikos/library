create table lendingBook(
    isbn char(13),
    title varchar(100) not null,
    copyNumber int check (copyNumber > 0),
    itemsLended int check (itemsLended >= 0 and itemsLended <= copyNumber),
    primary key (isbn),
    constraint UC_LendingBook unique (title)
);