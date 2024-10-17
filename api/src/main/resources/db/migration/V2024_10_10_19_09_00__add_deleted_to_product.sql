alter table products
    add column if not exists deleted boolean not null default false;

alter table products
    alter column deleted drop default;
