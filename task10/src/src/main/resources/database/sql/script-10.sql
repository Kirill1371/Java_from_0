SELECT model
FROM "Printer"
WHERE price = (SELECT MAX(price) FROM "Printer" )