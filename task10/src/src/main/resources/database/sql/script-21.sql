SELECT p.maker, MAX(pc.price) AS max_price
FROM "Product" p, "pc" pc
WHERE p.model IN (SELECT model FROM "pc")
GROUP BY maker;
