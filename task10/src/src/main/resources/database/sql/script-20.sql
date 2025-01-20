SELECT maker, COUNT(DISTINCT model) AS model_count
FROM "Product"
WHERE model IN (SELECT model FROM "pc")
GROUP BY maker
HAVING COUNT(DISTINCT model) >= 3;
