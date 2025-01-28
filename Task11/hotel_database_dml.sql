-- DML Script for populating Hotel Management Database with random data
-- Inserting into room
INSERT INTO room (number, status, price, capacity, stars) VALUES
(101, 'available', 120.00, 2, 3),
(102, 'occupied', 200.00, 4, 4),
(103, 'maintenance', 150.00, 2, 3),
(104, 'available', 180.00, 3, 5),
(105, 'occupied', 220.00, 4, 4);

-- Inserting into guest
INSERT INTO guest (name) VALUES
('Alice Johnson'),
('Bob Smith'),
('Charlie Brown'),
('Diana Prince'),
('Ethan Hunt');

-- Inserting into service
INSERT INTO service (name, price, category, date) VALUES
('Room Cleaning', 30.00, 'Housekeeping', '2025-01-20'),
('Spa Treatment', 100.00, 'Wellness', '2025-01-21'),
('Breakfast', 20.00, 'Dining', '2025-01-22'),
('Laundry', 15.00, 'Housekeeping', '2025-01-23'),
('Gym Access', 10.00, 'Fitness', '2025-01-24');

-- Inserting into stay
INSERT INTO stay (guestId, roomId, checkInDate, checkOutDate) VALUES
((SELECT id FROM guest WHERE name = 'Alice Johnson'), (SELECT id FROM room WHERE number = 101), '2025-01-15', '2025-01-20'),
((SELECT id FROM guest WHERE name = 'Bob Smith'), (SELECT id FROM room WHERE number = 102), '2025-01-10', '2025-01-14'),
((SELECT id FROM guest WHERE name = 'Charlie Brown'), (SELECT id FROM room WHERE number = 104), '2025-01-12', '2025-01-18'),
((SELECT id FROM guest WHERE name = 'Diana Prince'), (SELECT id FROM room WHERE number = 105), '2025-01-08', '2025-01-15'),
((SELECT id FROM guest WHERE name = 'Ethan Hunt'), (SELECT id FROM room WHERE number = 103), '2025-01-16', '2025-01-22');
