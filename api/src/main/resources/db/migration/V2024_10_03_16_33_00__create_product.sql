create table if not exists product_categories
(
    id             serial       not null primary key,
    created_at     timestamp,
    updated_at     timestamp,

    responsible_id integer      not null,
    name           varchar(255) not null
);

alter table product_categories
    drop constraint if exists FK_product_category_manager;
alter table product_categories
    add constraint FK_product_category_manager
        foreign key (responsible_id)
            references managers;

create table if not exists filter_categories
(
    id                  serial       not null primary key,
    created_at          timestamp,
    updated_at          timestamp,

    responsible_id      integer      not null,
    product_category_id integer      not null,
    name                varchar(255) not null
);

alter table filter_categories
    drop constraint if exists FK_filters_category_manager;
alter table filter_categories
    add constraint FK_filters_category_manager
        foreign key (responsible_id)
            references managers;

alter table filter_categories
    drop constraint if exists FK_filters_category_product_cat;
alter table filter_categories
    add constraint FK_filters_category_product_cat
        foreign key (product_category_id)
            references product_categories;

create table if not exists filters
(
    id                  serial       not null primary key,
    created_at          timestamp,
    updated_at          timestamp,

    filters_category_id integer      not null,
    responsible_id      integer      not null,
    name                varchar(255) not null
);

alter table filters
    drop constraint if exists FK_filter_filter_category;
alter table filters
    add constraint FK_filter_filter_category
        foreign key (filters_category_id)
            references filter_categories;

alter table filters
    drop constraint if exists FK_filter_manager;
alter table filters
    add constraint FK_filter_manager
        foreign key (responsible_id)
            references managers;

create table if not exists products
(
    id                  serial       not null primary key,
    created_at          timestamp,
    updated_at          timestamp,

    product_category_id integer,
    seller_id           integer      not null,
    name                varchar(255) not null,
    description         varchar(255),
    cost                float        not null,
    count               integer      not null
);

alter table products
    drop constraint if exists FK_product_product_category;
alter table products
    add constraint FK_product_product_category
        foreign key (product_category_id)
            references product_categories;

alter table products
    drop constraint if exists FK_product_seller;
alter table products
    add constraint FK_product_seller
        foreign key (seller_id)
            references sellers (id);

create table if not exists product_to_filter
(
    product_id integer not null,
    filter_id  integer not null,
    primary key (product_id, filter_id)
);

alter table product_to_filter
    drop constraint if exists FK_product_filter_product;
alter table product_to_filter
    add constraint FK_product_filter_product
        foreign key (product_id)
            references products;

alter table product_to_filter
    drop constraint if exists FK_product_filter_filter;
alter table product_to_filter
    add constraint FK_product_filter_filter
        foreign key (filter_id)
            references filters;

create table if not exists purchases
(
    id             serial       not null primary key,
    created_at     timestamp,
    updated_at     timestamp,

    customer_id    integer      not null,
    responsible_id integer      not null,
    date           timestamp    not null,
    status         varchar(255) not null
);

alter table purchases
    drop constraint if exists FK_purchase_customer;
alter table purchases
    add constraint FK_purchase_customer
        foreign key (customer_id)
            references customers;

alter table purchases
    drop constraint if exists FK_purchase_manager;
alter table purchases
    add constraint FK_purchase_manager
        foreign key (responsible_id)
            references managers;

create table if not exists purchase_product_info
(
    id          serial  not null primary key,
    created_at  timestamp,
    updated_at  timestamp,

    product_id  integer not null,
    purchase_id integer not null,
    count       integer not null
);

alter table purchase_product_info
    drop constraint if exists FK_purchase_product_info_product;
alter table purchase_product_info
    add constraint FK_purchase_product_info_product
        foreign key (product_id)
            references products;

alter table purchase_product_info
    drop constraint if exists FK_purchase_product_info_purchase;
alter table purchase_product_info
    add constraint FK_purchase_product_info_purchase
        foreign key (purchase_id)
            references purchases;

create table if not exists reviews
(
    id          serial  not null primary key,
    created_at  timestamp,
    updated_at  timestamp,

    product_id  integer not null,
    customer_id integer not null,
    grade       float   not null,
    description varchar(255)
);

alter table reviews
    drop constraint if exists FK_review_product;
alter table reviews
    add constraint FK_review_product
        foreign key (product_id)
            references products;

alter table reviews
    drop constraint if exists FK_review_customer;
alter table reviews
    add constraint FK_review_customer
        foreign key (customer_id)
            references customers;
