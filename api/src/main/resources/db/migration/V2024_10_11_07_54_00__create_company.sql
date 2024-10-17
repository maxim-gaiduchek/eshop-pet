create table if not exists companies
(
    id         serial       not null primary key,
    created_at timestamp,
    updated_at timestamp,

    name       varchar(255) not null,
    seller_id  integer      not null,
    deleted    boolean      not null
);

alter table companies
    drop constraint if exists FK_company_seller;
alter table companies
    add constraint FK_company_seller
        foreign key (seller_id)
            references sellers;

insert into companies (created_at, updated_at, name, seller_id, deleted)
select now(), now(), u.surname || ' ' || u.name, u.id, false
from sellers s
         join public.users u on u.id = s.user_id;

alter table products
    add column if not exists company_id integer;

update products
set company_id = (select c.id
                  from companies c
                  where c.seller_id = products.seller_id);

alter table products
    alter column company_id
        set not null;

alter table products
    drop column if exists seller_id;
