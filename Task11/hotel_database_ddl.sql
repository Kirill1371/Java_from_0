-- DDL Script for Hotel Management System
-- Creating tables in PostgreSQL

CREATE TABLE room (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    number INTEGER NOT NULL,
    status VARCHAR(50) NOT NULL,
    price DOUBLE PRECISION NOT NULL,
    capacity INTEGER NOT NULL,
    stars INTEGER NOT NULL
);

CREATE TABLE guest (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL
);

CREATE TABLE service (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    price DOUBLE PRECISION NOT NULL,
    category VARCHAR(100) NOT NULL,
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE stay (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    guestId UUID NOT NULL,
    roomId UUID NOT NULL,
    checkInDate TIMESTAMP NOT NULL,
    checkOutDate TIMESTAMP NOT NULL,
    FOREIGN KEY (guestId) REFERENCES guest (id) ON DELETE CASCADE,
    FOREIGN KEY (roomId) REFERENCES room (id) ON DELETE CASCADE
);
