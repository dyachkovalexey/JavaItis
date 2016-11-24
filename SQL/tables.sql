CREATE TABLE messagesDB
(
    message_id SERIAL PRIMARY KEY NOT NULL,
    message_text character varying(100) NOT NULL,
    message_user integer,
    chat_id integer
);

CREATE TABLE ChatsDB
(
    chat_id SERIAL PRIMARY KEY NOT NULL,
    chat_name character varying(40) NOT NULL
);

CREATE TABLE ChatUsersDB
(
    user_id SERIAL PRIMARY KEY NOT NULL,
    user_name character varying(20) NOT NULL,
    user_login character varying(30) NOT NULL,
    user_password integer NOT NULL
);

CREATE TABLE UsersChatIdDB
(
    user_id integer,
    chat_id integer
);