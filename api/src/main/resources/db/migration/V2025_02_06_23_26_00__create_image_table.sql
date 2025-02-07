create table if not exists images
(
    id          serial       not null primary key,
    created_at  timestamp,
    updated_at  timestamp,

    url         varchar(255) not null,
    description varchar(255) not null
);

alter table products
    add if not exists image_id integer;

alter table products
    drop constraint if exists FK_product_image;
alter table products
    add constraint FK_product_image
        foreign key (image_id)
            references images;
