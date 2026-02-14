CREATE TABLE roles
(

    id          SERIAL PRIMARY KEY,
    title       VARCHAR(30) NOT NULL
    level       VARCHAR(30) NOT NULL
    description VARCHAR(140) NOT NULL
    is_manager  BOOLEAN NOT NULL


);