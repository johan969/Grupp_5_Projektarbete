CREATE TABLE roles
(

    id          SERIAL PRIMARY KEY,
    title       VARCHAR(30),
    level       VARCHAR(30),
    description VARCHAR(140),
    is_manager  BOOLEAN NOT NULL


);