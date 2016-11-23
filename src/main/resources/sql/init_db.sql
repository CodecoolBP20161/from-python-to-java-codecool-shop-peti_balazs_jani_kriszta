DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS product_categories;
DROP TABLE IF EXISTS suppliers;

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
