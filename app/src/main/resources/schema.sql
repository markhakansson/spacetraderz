-- All everything related to users goes here.
CREATE SCHEMA IF NOT EXISTS auth;

-- Users table.
CREATE TABLE IF NOT EXISTS auth.users (
    uuid uuid NOT NULL,
    username character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    email_address character varying(255) NOT NULL
);
ALTER TABLE auth.users OWNER TO markhakansson;


-- Data related to the game itself.
CREATE SCHEMA IF NOT EXISTS game;
-- Agents table.
CREATE TABLE IF NOT EXISTS game.agents (
    -- This should be the token received from SpaceTraders after registering.
    uuid uuid NOT NULL,
    symbol character varying(255) NOT NULL,
    name character varying(255) NOT NULL
);
ALTER TABLE game.agents OWNER TO markhakansson;