insert into ORDERS (ORDER_ID, ORDER_PRICE, ORDER_VAT)
values (1, null, null);
insert into ORDER_ITEMS (ORDER_ITEM_ID, ORDER_ID, PRODUCT_ID, QUANTITY, PRICE, VAT)
values (1, 1, 2, 99, null, null);
insert into ORDER_ITEMS (ORDER_ITEM_ID, ORDER_ID, PRODUCT_ID, QUANTITY, PRICE, VAT)
values (2, 1, 1, 10, null, null);
insert into ORDERS (ORDER_ID, ORDER_PRICE, ORDER_VAT)
values (2, null, null);
insert into ORDER_ITEMS (ORDER_ITEM_ID, ORDER_ID, PRODUCT_ID, QUANTITY, PRICE, VAT)
values (3, 2, 3, 10, null, null);
insert into ORDER_ITEMS (ORDER_ITEM_ID, ORDER_ID, PRODUCT_ID, QUANTITY, PRICE, VAT)
values (4, 2, 4, 72, null, null);
insert into PRODUCTS (NAME, UNIT_PRICE, VAT_RATE)
values('bacon',1.972,10);
alter table ORDERS
    alter column order_id restart with 3;
alter table ORDER_ITEMS
    alter column order_item_id restart with 5;
