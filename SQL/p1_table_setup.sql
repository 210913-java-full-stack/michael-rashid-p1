#Make new database
DROP DATABASE IF EXISTS projectOne;
CREATE DATABASE projectOne;

USE projectOne;

DROP TABLE IF EXISTS user_info;
DROP TABLE IF EXISTS flights;
DROP TABLE IF EXISTS user_flights;

CREATE TABLE flights
(
    flight_id 		INT AUTO_INCREMENT,
    origin_city		VARCHAR(100),
    origin_state	VARCHAR(2),
    destination_city VARCHAR(100),
    destination_state VARCHAR(2),
    num_seats		INT,
    CONSTRAINT flights_pk PRIMARY KEY (flight_id)
);

CREATE TABLE user_info
(
	user_id		INT AUTO_INCREMENT,
	username 	VARCHAR(100) NOT NULL,
    password	VARCHAR(20) NOT NULL,
    first_name 	VARCHAR(20) NOT NULL,
    last_name	VARCHAR(20) NOT NULL,
    user_role	VARCHAR(20) NOT NULL,
    CONSTRAINT users_pk PRIMARY KEY (user_id)
);

CREATE TABLE user_flights
(
	junction_id INT AUTO_INCREMENT,
	flight_id 	INT NOT NULL,
	user_id 	INT NOT NULL,
	checkin_status BOOL,
	num_tickets	INT,
	INDEX (flight_id),
	INDEX (user_id),
	CONSTRAINT user_flights_fk PRIMARY KEY (junction_id),
	CONSTRAINT flights_fk FOREIGN KEY (flight_id) REFERENCES flights (flight_id),
	CONSTRAINT users_fk FOREIGN KEY (user_id) REFERENCES user_info (user_id)
);

##Fill with some dummy data

INSERT INTO user_info(username, password, first_name, last_name, user_role) VALUES ("johnsmith1", "password", "John", "Smith", "Passenger");
INSERT INTO flights(origin_city, origin_state, destination_city, destination_state, num_seats) VALUES ("Orlando", "FL", "Kansas City", "MO", 50);
INSERT INTO user_flights(flight_id, user_id, checkin_status, num_tickets) VALUES (1,1,false,3);

UPDATE flights SET num_seats = (num_seats - 3) WHERE flight_id = 1;