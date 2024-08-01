CREATE TABLE task (
                      id SERIAL PRIMARY KEY,
                      name VARCHAR(255) NOT NULL,
                      description TEXT,
                      completed BOOLEAN NOT NULL
);