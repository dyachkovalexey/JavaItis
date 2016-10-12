CREATE TABLE auto (
	auto_id integer NOT NULL,
	auto_name character varying(40) NOT NULL,
	mileage integer NOT NULL,
	PRIMARY KEY(auto_id)	
);

CREATE TABLE car_owner (
	owner_id integer NOT NULL,
	FIO character varying(100) NOT NULL,
	owner_age integer NOT NULL,
	owner_city character varying(20) NOT NULL,
	PRIMARY KEY(owner_id)
);

CREATE TABLE auto_car_owner (
	auto_id integer NOT NULL,
	owner_id integer NOT NULL,
	PRIMARY KEY(owner_id, auto_id)
);