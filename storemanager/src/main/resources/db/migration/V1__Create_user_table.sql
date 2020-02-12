CREATE TABLE if not exists users (
    id uuid primary key ,
    email varchar not null ,
    password varchar not null ,
    first_name varchar not null ,
    last_name varchar not null ,
    created_at timestamp not null default current_timestamp,
    updated_at timestamp,
    deleted_at timestamp
);

CREATE TABLE if not exists roles (
 id uuid primary key ,
 name varchar not null,
 created_at timestamp,
 updated_at timestamp,
 deleted_at timestamp
);

create table if not exists users_roles (
    user_id uuid not null,
    role_id uuid not null
);

create unique index if not exists users_email on users (email);
create unique index if not exists roles_name on roles (name);

alter table users_roles drop constraint if exists user_roles_fk;
alter table users_roles drop constraint if exists roles_users_fk;

alter table users_roles
    add constraint user_roles_fk foreign key (user_id) references users (id) on update restrict on delete cascade;

alter table users_roles
   add constraint roles_users_fk foreign key  (role_id) references roles (id) on update restrict on delete cascade;