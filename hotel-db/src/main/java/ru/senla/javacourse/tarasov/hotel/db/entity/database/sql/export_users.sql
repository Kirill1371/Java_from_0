INSERT INTO users (username, password) VALUES
('admin', '$2a$10$xVCH4IAHwYQ4L0LZzKlfU.3uQ9vq3D7bB7j6X1tNk7JvZU1fVYJXa'); -- password: admin

INSERT INTO user_roles (user_id, role) VALUES
(1, 'ROLE_ADMIN'),
(1, 'ROLE_USER');