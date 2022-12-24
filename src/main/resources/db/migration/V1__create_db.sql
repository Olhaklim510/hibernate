CREATE TABLE client (
    id IDENTITY PRIMARY KEY,
    name VARCHAR (200) NOT NULL CHECK (CHAR_LENGTH (name) >2)
);

CREATE TABLE planet (
    id VARCHAR (200) NOT NULL PRIMARY KEY CHECK (REGEXP_LIKE (id, '^[A-Z0-9]*$', 'c')),
    name VARCHAR (500) NOT NULL
);

CREATE TABLE ticket (
    id IDENTITY PRIMARY KEY,
    created_at TIMESTAMP,
    client_id BIGINT NOT NULL,
    from_planet_id VARCHAR NOT NULL,
    to_planet_id VARCHAR NOT NULL,
    FOREIGN KEY (client_id) REFERENCES client(id) ON DELETE CASCADE,
    FOREIGN KEY (from_planet_id) REFERENCES planet(id) ON DELETE CASCADE,
    FOREIGN KEY (to_planet_id) REFERENCES planet(id) ON DELETE CASCADE
);