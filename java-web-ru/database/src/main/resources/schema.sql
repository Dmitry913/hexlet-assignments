-- BEGIN
DROP TABLE IF EXISTS products;

CREATE TABLE IF NOT EXISTS products (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    price INT
);
-- END
