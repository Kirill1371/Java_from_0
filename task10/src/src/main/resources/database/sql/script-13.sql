SELECT AVG(speed)
FROM "pc"
WHERE model IN (SELECT model FROM "Product" WHERE maker LIKE 'A%')