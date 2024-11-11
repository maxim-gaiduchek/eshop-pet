create table if not exists users
(
    id         serial       not null primary key,
    created_at timestamp,
    updated_at timestamp,

    name       varchar(255) not null,
    surname    varchar(255) not null,
    email      varchar(255) not null,
    phone      varchar(255) not null,
    role       varchar(255) not null
);

create table if not exists customers
(
    user_id serial       not null primary key,

    address varchar(255) not null
);

alter table customers
    drop constraint if exists FK_customer_user;
alter table customers
    add constraint FK_customer_user
        foreign key (user_id)
            references users;

create table if not exists managers
(
    user_id serial not null primary key
);

alter table managers
    drop constraint if exists FK_manager_user;
alter table managers
    add constraint FK_manager_user
        foreign key (user_id)
            references users;

create table if not exists sellers
(
    user_id serial       not null primary key,

    address varchar(255) not null
);

alter table sellers
    drop constraint if exists FK_seller_user;
alter table sellers
    add constraint FK_seller_user
        foreign key (user_id)
            references users;
