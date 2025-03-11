SELECT model, speed, hd
FROM "pc"
WHERE ((speed = '12' OR speed = '24') AND price < 600)