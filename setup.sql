CREATE DATABASE techExamDB;

USE techExamDB;

CREATE TABLE product (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    type ENUM('food', 'sports', 'household', 'music', 'electronic', 'appliance') NOT NULL,
    quantity INT NOT NULL,
    unit_price DECIMAL(10, 2) NOT NULL,
    requirements TEXT
);
