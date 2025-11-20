-- V1__create_ducks_table.sql
-- This is gonna create a table for my Ducks
-- Which is gonna be like my users in this database

CREATE TABLE ducks (
                       id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                       username VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       duck_roles VARCHAR(50) NOT NULL DEFAULT 'USER',
                       created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       account_non_expired BOOLEAN NOT NULL DEFAULT true,
                       account_non_locked BOOLEAN NOT NULL DEFAULT true,
                       credentials_non_expired BOOLEAN NOT NULL DEFAULT true,
                       enabled BOOLEAN NOT NULL DEFAULT true
);

-- We can use this index to search quickly for a user
-- Not really needed in my case, but good practice
-- And am trying to learn more about indexes and new things
CREATE INDEX idx_ducks_username ON ducks(username);
CREATE INDEX idx_ducks_email ON ducks(email);