create table products
(
    product_id bigint auto_increment primary key,
    name       varchar(255),
    vat_rate   int,
    unit_price decimal(19, 3)
);

create table orders
(
    order_id    bigint auto_increment primary key,
    order_price decimal(19, 3),
    order_vat   decimal(19, 3)
);

create table order_items (
    order_item_id bigint auto_increment primary key,
    order_id      bigint,
    product_id    bigint,
    quantity      int,
    price         decimal(19, 3),
    vat           decimal(19, 3),
    foreign key (order_id) references orders(order_id),
    foreign key (product_id) references products(product_id)
);

