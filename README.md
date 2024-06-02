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
https://github.com/RedRoSeE-API/CraftExerice.git
cd craft-exercise
```

### 2. Configure the Database
Create a MySQL database and a table to store user information. Use the following SQL script or the file CraftExercise.sql located in the repository:

```sql
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
```

### 3. Configure Environment Variables

3.1 Set the following environment variables in your IDE or your environment:
- `GITHUB_TOKEN`: Your GitHub personal access token
- `FRESHDESK_TOKEN`: Your Freshdesk API token

3.2 Update the `src/main/resources/config.properties` and file with your database credentials:
    ```properties
    db.url=jdbc:mysql://localhost:3306/
    db.database=your_database_name
    db.username=your_username
    db.password=your_password
    ```
### Additional Notes
Ensure your MySQL server is running and accessible.
Replace placeholders in config.properties with your actual credentials.
This project does not use any framework like Spring for simplicity and demonstration purposes.

### Acknowledgements
* JUnit 5
* Mockito
* Jackson Databind
* MySQL Connector/J

### Feel free to adjust paths, URLs, and other specific details to fit your project structure and requirements.

