CREATE TABLE department
(
    id                  BIGSERIAL PRIMARY KEY,
    department_name     VARCHAR(255) NOT NULL UNIQUE,
    cost_center         INTEGER      NOT NULL UNIQUE,
    location            VARCHAR(255),
    number_of_employees INTEGER      NOT NULL
);
