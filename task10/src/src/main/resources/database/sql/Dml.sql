-- Заполнение таблицы Product
INSERT INTO Product (maker, model, type) VALUES
('Dell', 'XPS13', 'Laptop'),
('HP', 'Pavilion15', 'Laptop'),
('Canon', 'PIXMA', 'Printer'),
('Acer', 'Aspire5', 'PC');

-- Заполнение таблицы PC
INSERT INTO pc (model, speed, ram, hd, cd, price) VALUES
('Aspire5', 3.6, 16, 512, '8x', 1200);

-- Заполнение таблицы Laptop
INSERT INTO Laptop (model, speed, ram, hd, screen, price) VALUES
('XPS13', 4.2, 32, 1024, 13.3, 2000),
('Pavilion15', 3.1, 8, 256, 15.6, 900);

-- Заполнение таблицы Printer
INSERT INTO Printer (model, color, type, price) VALUES
('PIXMA', 'Y', 'Jet', 150);
