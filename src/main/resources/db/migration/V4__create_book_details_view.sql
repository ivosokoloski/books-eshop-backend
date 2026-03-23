CREATE VIEW book_details_view AS
SELECT
    b.id AS id,
    b.name AS book_name,
    b.category AS category,
    b.state AS state,
    b.available_copies AS available_copies,
    a.name || ' ' || a.surname AS author_full_name,
    c.name AS country_name
FROM books b
         JOIN authors a ON b.author_id = a.id
         JOIN countries c ON a.country_id = c.id;