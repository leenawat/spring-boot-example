CREATE TABLE book (
    id BIGINT NOT NULL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    category VARCHAR(100)
);

CREATE TABLE person  (
    person_id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    age INTEGER,
    is_active BOOLEAN
);

CREATE TABLE config (
    id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    app_key VARCHAR(255) UNIQUE,
    app_value VARCHAR(255)
);

CREATE TABLE unit (
    id BIGINT NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT
);