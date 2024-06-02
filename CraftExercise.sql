CREATE DATABASE creaft_exercise;
USE craft_exercise; 

CREATE TABLE user_information (
	id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    twitter_id VARCHAR(255) NOT NULL,
    unique_external_id VARCHAR(255) NOT NULL,
    creation_time TIMESTAMP NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO user_information (name, email, twitter_id, unique_external_id, creation_time)
VALUES ("test", "test.test@gmail.com", "@test1", "test123", CURRENT_TIMESTAMP);