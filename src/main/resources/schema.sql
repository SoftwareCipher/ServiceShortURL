DROP TABLE IF EXISTS shorter;

CREATE TABLE IF NOT EXISTS shorter
(
    id          IDENTITY PRIMARY KEY,
    hash         varchar(20) not null unique,
    original_url varchar,
    created_at   timestamp
);
