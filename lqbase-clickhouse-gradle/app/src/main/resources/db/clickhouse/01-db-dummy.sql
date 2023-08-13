--liquibase formatted sql

--changeset johndoe:create-dummy-database

CREATE DATABASE IF NOT EXISTS dummy;