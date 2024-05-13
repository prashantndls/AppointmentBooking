create table appointables (
    _id BIGINT PRIMARY KEY AUTO_INCREMENT,
    _name VARCHAR(255) NOT NULL,
    _email VARCHAR(255) UNIQUE NOT NULL,
    _address VARCHAR(255) NOT NULL,
    _days_available VARCHAR(255) NOT NULL,
    _times_available VARCHAR(255) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP
);