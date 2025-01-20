SELECT p.model, pc.price
FROM "Product" p
JOIN "pc" ON p.model = pc.model
WHERE p.maker LIKE 'B%'

UNION

SELECT p.model, l.price
FROM "Product" p
JOIN "Laptop" l ON p.model = l.model
WHERE p.maker LIKE 'B%'

UNION

SELECT p.model, pr.price
FROM "Product" p
JOIN "Printer" pr ON p.model = pr.model
WHERE p.maker LIKE 'B%';
