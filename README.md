# Craft Exercise

## Overview

This project is a Java-based application that interacts with GitHub and Freshdesk APIs. It includes functionalities for fetching user data from GitHub, managing contacts in Freshdesk, and persisting user information into a MySQL database.

## Features

- Fetch user data from GitHub using OAuth token.
- Manage Freshdesk contacts with API integration.
- Persist user information into a MySQL database.
- Unit tests for the service layer.

## Prerequisites

- Java 11 or later
- Maven
- MySQL
- GitHub and Freshdesk accounts

## Setup

### 1. Clone the Repository

```sh
git clone https://github.com/yourusername/craft-exercise.git
cd craft-exercise
```

2. Configure the Database
Create a MySQL database and a table to store user information. Use the following SQL script or the file CraftExercise.sql located :

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

