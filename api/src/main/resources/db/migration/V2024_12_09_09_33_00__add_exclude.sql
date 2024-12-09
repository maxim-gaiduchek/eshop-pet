alter table filters
    add column if not exists exclude boolean not null default false;

insert into filters (created_at, updated_at, filter_category_id, responsible_id, name, exclude)
select now(), now(), fc.id, 1, 'Other', true
from filter_categories fc;

alter table filters
    alter column exclude
        drop default;
