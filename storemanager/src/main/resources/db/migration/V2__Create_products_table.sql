create table if not exists products (
    id uuid primary key,
    name varchar(100) not null ,
    price decimal not null,
    quantity decimal not null,
    created_at timestamp not null default current_timestamp,
    updated_at timestamp,
    deleted_at timestamp
);

create table if not exists sales (
    id uuid primary key,
    revenue decimal not null,
    created_at timestamp not null default current_timestamp,
    updated_at timestamp,
    deleted_at timestamp
);

create table if not exists products_sales_user (
    product_id uuid not null ,
    sales_id uuid not null,
    users_id uuid not null
);

alter table products_sales_user drop constraint if exists products_sales_user_fk;
alter table products_sales_user drop constraint if exists sales_product_user_fk;
alter table products_sales_user drop constraint if exists user_product_sales_fk;

alter table products_sales_user
    add constraint products_sales_user_fk foreign key (product_id) references products (id) on update restrict on delete cascade;
alter table products_sales_user
    add constraint sales_product_user_fk foreign key (sales_id)references sales (id) on update restrict on delete cascade;
alter table products_sales_user
    add constraint user_product_sales_fk foreign key (users_id)references users (id) on update restrict on delete cascade;
