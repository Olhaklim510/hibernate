INSERT INTO client (name) VALUES
('Adidas'),
('Geox'),
('Puma'),
('Nike'),
('Skechers'),
('UGG'),
('Timberland'),
('Columbia'),
('Reebok'),
('Fila');

INSERT INTO planet (id, name) VALUES
('1MERCURY', 'Mercury'),
('2VENUS', 'Venus'),
('3EARTH', 'Earth'),
('4MARS', 'Mars'),
('5JUPITER', 'Jupiter'),
('6SATURN', 'Saturn'),
('7URANUS', 'Uranus'),
('8NEPTUNE', 'Neptune');

INSERT INTO ticket (created_at, client_id, from_planet_id, to_planet_id) VALUES
(CURRENT_TIMESTAMP(), 1, '1MERCURY', '2VENUS'),
(CURRENT_TIMESTAMP(), 2, '2VENUS', '3EARTH'),
(CURRENT_TIMESTAMP(), 3, '3EARTH', '4MARS'),
(CURRENT_TIMESTAMP(), 4, '4MARS', '5JUPITER'),
(CURRENT_TIMESTAMP(), 5, '5JUPITER', '6SATURN'),
(CURRENT_TIMESTAMP(), 6, '6SATURN', '7URANUS'),
(CURRENT_TIMESTAMP(), 7, '7URANUS', '8NEPTUNE'),
(CURRENT_TIMESTAMP(), 8, '8NEPTUNE', '1MERCURY'),
(CURRENT_TIMESTAMP(), 9, '1MERCURY', '8NEPTUNE'),
(CURRENT_TIMESTAMP(), 10, '7URANUS', '3EARTH');