SELECT speed, AVG(price)
FROM "pc"
WHERE speed > 800
GROUP BY speed;
