CREATE DATABASE mydb;

use mydb;

CREATE TABLE IF NOT EXISTS materials (
	material_id INT NOT NULL AUTO_INCREMENT,
    material_name VARCHAR(45) NOT NULL,
    material_cost int NOT NULL,
    PRIMARY KEY(material_id)
);

CREATE TABLE IF NOT EXISTS workers (
	worker_id INT NOT NULL AUTO_INCREMENT,
    worker_name VARCHAR(64) NOT NULL,
    worker_salary int NOT NULL,
    PRIMARY KEY(worker_id)
);

CREATE TABLE IF NOT EXISTS product (
	product_id INT NOT NULL AUTO_INCREMENT,
    material_id INT NOT NULL,
    worker_id INT NULL,
    making_time int NOT NULL,
    PRIMARY KEY(product_id),
    FOREIGN KEY (material_id) REFERENCES materials(material_id),
    FOREIGN KEY (worker_id) REFERENCES workers(worker_id)
);