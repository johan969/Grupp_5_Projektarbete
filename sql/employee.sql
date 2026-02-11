CREATE TABLE employee
(
    employee_id           BIGSERIAL PRIMARY KEY,
    name                  VARCHAR(100) NOT NULL,
    monthly_salary        INT          NOT NULL,
    employment_type       VARCHAR(50)  NOT NULL,
    employment_percentage INT          NOT NULL,
    employee_type         VARCHAR(50)  NOT NULL
);