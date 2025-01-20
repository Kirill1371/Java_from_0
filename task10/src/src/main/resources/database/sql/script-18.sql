SELECT p.maker, pr.price
FROM "Product" p, "Printer" pr
WHERE p.model IN (SELECT pr.model FROM "Printer" WHERE color = 'Y')
AND pr.price = (SELECT MIN(price) FROM "Printer" WHERE color = 'Y');
