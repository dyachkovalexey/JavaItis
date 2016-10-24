CREATE TABLE auto (
	auto_id SERIAL PRIMARY KEY NOT NULL,
	auto_name character varying(40) NOT NULL,
	auto_number character varying(40) NOT NULL	
);

CREATE TABLE users (
	user_id SERIAL PRIMARY KEY NOT NULL,
	user_name character varying(50) NOT NULL,
	user_login character varying(50) NOT NULL,
	user_password integer NOT NULL,
	user_token character varying(100) NOT NULL
);

CREATE TABLE auto_user (
	user_id SERIAL PRIMARY KEY NOT NULL,
	auto_id integer NOT NULL
);