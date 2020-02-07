--CREATE USER imdb_super_admin IF NOT EXISTS
DO $create_super_user$
BEGIN
	IF NOT EXISTS (
			SELECT
			FROM pg_catalog.pg_user
			WHERE usename = 'imdb_super_admin'
			)
			THEN
				CREATE USER imdb_super_admin WITH 
					ENCRYPTED PASSWORD 'imdb_super_admin'
					SUPERUSER 
					CREATEDB 
					CREATEROLE 
					REPLICATION 
					BYPASSRLS;
		END IF;
END
$create_super_user$;

--CREATE USER imdb_production IF NOT EXISTS
DO $create_query_user$
BEGIN
	IF NOT EXISTS (
			SELECT
			FROM pg_catalog.pg_user
			WHERE usename = 'imdb_production'
			)
			THEN
				CREATE USER imdb_production WITH 
					ENCRYPTED PASSWORD 'imdb_production';
		END IF;
END
$create_query_user$;

SET ROLE imdb_super_admin;

--CREATE DATABASE CSCI_620 IF NOT EXISTS loop around
SELECT 'CREATE DATABASE csci_620 WITH OWNER = imdb_super_admin ENCODING = ''UTF8'''
WHERE NOT EXISTS (SELECT * FROM pg_database WHERE datname = 'csci_620'); \gexec

\connect csci_620;

--CREATE SCHEMA imdb IF NOT EXISTS
DO $create_imdb_schema$
BEGIN
	CREATE SCHEMA IF NOT EXISTS imdb AUTHORIZATION CURRENT_USER;
END
$create_imdb_schema$;

--CREATE TABLE names
CREATE TABLE IF NOT EXISTS imdb.names (
	id INTEGER NOT NULL PRIMARY KEY,
	primaryName VARCHAR(200) NOT NULL,
	birthYear NUMERIC(4, 0),
	deathYear NUMERIC(4, 0)
);

--CREATE TABLE titles
CREATE TABLE IF NOT EXISTS imdb.titles (
	id INTEGER NOT NULL PRIMARY KEY,
	type VARCHAR(100),
	primaryTitleName VARCHAR(1000) NOT NULL,
	originalTitleName VARCHAR(1000) NOT NULL,
	isAdult BOOLEAN NOT NULL DEFAULT FALSE,
	startYear NUMERIC(4, 0),
	endYear NUMERIC(4, 0),
	runtimeMinutes INTEGER,
	genres VARCHAR(200)
);

--CREATE TABLE ratings
CREATE TABLE IF NOT EXISTS imdb.ratings (
	titleId INTEGER NOT NULL PRIMARY KEY,
	averageRating NUMERIC(4, 2),
	voteCount INTEGER,
	FOREIGN KEY (titleId) REFERENCES imdb.titles(id)
);

--CREATE TABLE episode
CREATE TABLE IF NOT EXISTS imdb.episodes (
	id INTEGER NOT NULL PRIMARY KEY,
	titleId INTEGER NOT NULL,
	seasonNumber INTEGER,
	episodeNumber INTEGER,
	FOREIGN KEY (titleId) REFERENCES imdb.titles(id)
);

--CREATE TABLE akas
CREATE TABLE IF NOT EXISTS imdb.akas (
	titleId INTEGER NOT NULL PRIMARY KEY,
	orderNumber INTEGER,
	titleName VARCHAR(200),
	region VARCHAR(200),
	language VARCHAR(200),
	types VARCHAR(500),
	attributes VARCHAR(500),
	isOriginalTitle BOOLEAN,
	FOREIGN KEY (titleId) REFERENCES imdb.titles(id)
);

--CREATE TABLE directors
CREATE TABLE IF NOT EXISTS imdb.directors (
	titleId INTEGER NOT NULL,
	nameId INTEGER NOT NULL,
	PRIMARY KEY (titleId, nameId),
	FOREIGN KEY (titleId) REFERENCES imdb.titles(id),
	FOREIGN KEY (nameId) REFERENCES imdb.names(id)
);

--CREATE TABLE writers
CREATE TABLE IF NOT EXISTS imdb.writers (
	titleId INTEGER NOT NULL,
	nameId INTEGER NOT NULL,
	PRIMARY KEY (titleId, nameId),
	FOREIGN KEY (titleId) REFERENCES imdb.titles(id),
	FOREIGN KEY (nameId) REFERENCES imdb.names(id)
);

--CREATE TABLE principals
CREATE TABLE IF NOT EXISTS imdb.principals (
	titleId INTEGER NOT NULL,
	nameId INTEGER NOT NULL,
	orderNumber INTEGER,
	category VARCHAR(100),
	job VARCHAR(1000),
	characters VARCHAR(1000),
	PRIMARY KEY (titleId, nameId),
	FOREIGN KEY (titleId) REFERENCES imdb.titles(id),
	FOREIGN KEY (nameId) REFERENCES imdb.names(id)
);

\echo 'DB INITIALIZED'