-- Product category

alter table product_categories
    alter column responsible_id
        drop not null;

alter table product_categories
    drop constraint if exists FK_product_category_manager;

update product_categories
set responsible_id = (select min(id) from administrators);

alter table product_categories
    add constraint FK_product_category_responsible
        foreign key (responsible_id)
            references administrators;

alter table product_categories
    alter column responsible_id
        set not null;

-- Filter category

alter table filter_categories
    alter column responsible_id
        drop not null;

alter table filter_categories
    drop constraint if exists FK_filters_category_manager;

update filter_categories
set responsible_id = (select min(id) from administrators);

alter table filter_categories
    add constraint FK_filter_category_responsible
        foreign key (responsible_id)
            references administrators;

alter table filter_categories
    alter column responsible_id
        set not null;

-- Filter

alter table filters
    alter column responsible_id
        drop not null;

alter table filters
    drop constraint if exists FK_filter_manager;

update filters
set responsible_id = (select min(id) from administrators);

alter table filters
    add constraint FK_filter_responsible
        foreign key (responsible_id)
            references administrators;

alter table filters
    alter column responsible_id
        set not null;

-- Deleted flag

alter table product_categories
    add column if not exists deleted boolean not null default false;

alter table product_categories
    alter column deleted
        drop default;

alter table filter_categories
    add column if not exists deleted boolean not null default false;

alter table filter_categories
    alter column deleted
        drop default;

alter table filters
    add column if not exists deleted boolean not null default false;

alter table filters
    alter column deleted
        drop default;
