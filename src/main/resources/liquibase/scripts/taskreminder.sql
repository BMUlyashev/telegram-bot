-- liquibase formatted sql

-- changeset bulyashev:1
CREATE TABLE planner(
    id BIGSERIAL,
    chatId BIGINT,
    task TEXT,
    timeTask TIMESTAMP
);