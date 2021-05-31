create table authors
(
    id          bigserial      not null
        constraint authors_pk
            primary key,
    first_name  varchar(255) not null,
    middle_name varchar(255) not null,
    last_name   varchar(255) not null

);

create table books
(
    id     bigserial      not null
        constraint book_pk
            primary key,
    name   varchar(255) not null,
    author bigint
        constraint fk__author
            references authors,
    cost   integer
);

-- embeddable
create table book_genres
(
    book_id bigserial
        constraint fk__book_genre
            references books,
    genre   varchar(255) -- enum
);

create table buyers
(
    id   bigserial      not null
        constraint buyers_pk
            primary key,
    name varchar(255) not null
);

create table orders
(
    id         bigserial not null
        constraint orders_pk
            primary key,
    create_date timestamp,
    buyer      bigserial
        constraint fk__buyers
            references buyers,
    sum        integer
);

create table book_order
(
    book_id  integer
        constraint fk__book_order__book
            references books,
    order_id integer
        constraint fk__book_order__order
            references orders
);

create table online_orders
(
    id      bigserial not null
        constraint online_orders_pk
            references orders
        primary key,
    address varchar(255) --адрес доставки
);
