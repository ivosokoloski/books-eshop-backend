CREATE TABLE library_users (
                               id BIGSERIAL PRIMARY KEY,
                               name VARCHAR(255),
                               surname VARCHAR(255),
                               email VARCHAR(255)
);

CREATE TABLE book_lending (
                              id BIGSERIAL PRIMARY KEY,
                              book_id BIGINT REFERENCES books(id),
                              user_id BIGINT REFERENCES library_users(id)
);


INSERT INTO library_users (name, surname, email) VALUES ('Ivo', 'Sokoloski', 'ivo@example.com');
INSERT INTO library_users (name, surname, email) VALUES ('Petar', 'Petrov', 'petar@example.com');


INSERT INTO book_lending (book_id, user_id) VALUES (1, 1);
INSERT INTO book_lending (book_id, user_id) VALUES (1, 2);
INSERT INTO book_lending (book_id, user_id) VALUES (2, 1);