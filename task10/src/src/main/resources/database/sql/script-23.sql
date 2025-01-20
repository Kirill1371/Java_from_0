SELECT DISTINCT p.maker
FROM "Product" p
JOIN "pc" c ON p.model = c.model
JOIN "Laptop" l ON p.model = l.model
WHERE c.speed >= 750 AND l.speed >= 750;
