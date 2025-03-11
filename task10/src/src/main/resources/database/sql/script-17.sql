SELECT model, speed
FROM "Laptop" 
WHERE speed < ALL (SELECT speed FROM pc);
