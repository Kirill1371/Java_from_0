SELECT maker
FROM "Product"
WHERE model IN (SELECT model FROM "pc")
AND model NOT IN (SELECT model FROM "Laptop");
