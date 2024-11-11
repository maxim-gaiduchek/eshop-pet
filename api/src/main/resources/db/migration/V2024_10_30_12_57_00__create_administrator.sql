create table if not exists administrators
(
    user_id serial not null primary key
);

alter table administrators
    drop constraint if exists FK_administrator_user;
alter table administrators
    add constraint FK_administrator_user
        foreign key (user_id)
            references users;

insert into users (created_at, updated_at, name, surname, email, phone, role)
values (now(), now(), 'Admin', 'Main', 'maxim.gayduchek@gmail.com',
        '+420607777777', 'ROLE_ADMINISTRATOR');

insert into administrators (user_id)
select max(id)
from users
where role = 'ROLE_ADMINISTRATOR';

insert into security.user_auths (created_at, updated_at, user_id, password)
select now(), now(), max(id), 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3'
from users
where role = 'ROLE_ADMINISTRATOR';
