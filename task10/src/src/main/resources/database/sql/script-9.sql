SELECT maker
FROM "Product"
WHERE model IN (SELECT model FROM "pc" WHERE speed >= '450')