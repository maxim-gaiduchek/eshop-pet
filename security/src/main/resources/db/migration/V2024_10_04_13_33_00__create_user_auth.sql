create schema if not exists security;

create table if not exists security.user_auths
(
    id         serial       not null primary key,
    created_at timestamp,
    updated_at timestamp,

    user_id    integer      not null,
    password   varchar(260) not null
);

-- Uses only for audit
create table if not exists security.user_refresh_tokens
(
    id                 serial        not null primary key,
    created_at         timestamp,
    updated_at         timestamp,

    user_auth_id            integer       not null,
    user_refresh_token varchar(1023) not null
);
