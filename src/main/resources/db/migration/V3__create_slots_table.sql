create table slots (
    _id BIGINT PRIMARY KEY AUTO_INCREMENT,
    from_time DATETIME not null,
    to_time DATETIME not null,
    booked_to VARCHAR(255) default null,
    appointable_id BIGINT not null,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP
);