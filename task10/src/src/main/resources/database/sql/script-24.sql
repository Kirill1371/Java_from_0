SELECT p.model
FROM "Product" p, "Laptop" l
WHERE l.price = (SELECT MAX(price) FROM "Laptop");
