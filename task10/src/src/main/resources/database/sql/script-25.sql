SELECT pr.maker
FROM "Printer" p
JOIN "Product" pr ON p.model = pr.model
JOIN "pc" c ON pr.model = c.model
WHERE c.ram = (SELECT MIN(ram) FROM pc)
AND c.speed = (SELECT MAX(speed) FROM pc WHERE ram = (SELECT MIN(ram) FROM pc));
