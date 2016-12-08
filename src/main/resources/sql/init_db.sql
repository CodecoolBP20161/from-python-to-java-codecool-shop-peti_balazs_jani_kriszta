DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS product_categories;
DROP TABLE IF EXISTS suppliers;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS orders_switch;

CREATE TABLE product_categories
(
id SERIAL PRIMARY KEY,
name varchar(40),
department VARCHAR,
description varchar
);

CREATE TABLE suppliers
(
id SERIAL PRIMARY KEY,
name varchar(40),
description varchar
);
CREATE TABLE products
(
id SERIAL PRIMARY KEY,
name varchar(40),
category_id INTEGER,
supplier_id INTEGER,
description varchar,
default_price FLOAT,
default_currency VARCHAR,
FOREIGN KEY (category_id) REFERENCES product_categories(id),
FOREIGN KEY (supplier_id) REFERENCES suppliers(id)
);

CREATE TABLE users
(
id SERIAL PRIMARY KEY,
name varchar(40),
email varchar,
phone VARCHAR,
billing_address VARCHAR,
shipping_address VARCHAR
);

CREATE TABLE orders
(
id SERIAL PRIMARY KEY,
user_id INTEGER,
billing_address VARCHAR,
shipping_address VARCHAR,
total_price FLOAT,
date_time VARCHAR,
FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE orders_switch
(
id SERIAL PRIMARY KEY,
order_id INTEGER,
product_id INTEGER ,
price FLOAT ,
quantity INTEGER ,
FOREIGN KEY (order_id) REFERENCES orders(id),
FOREIGN KEY (product_id) REFERENCES products(id)
);
