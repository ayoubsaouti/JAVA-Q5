# Documentation

## Introduction
The Game Management system is a backend application designed to manage game sessions, track player statistics, and facilitate game participation. This application uses Spring Boot for backend development and communicates with a player management system through REST API endpoints.

## Table of Contents
1. [Endpoints](#endpoints)
2. [Business Logic](#business-logic)
3. [Database Structure](#database-structure)
4. [Application Workflow](#application-workflow)
5. [Player Management System](#player-management-system)

---

## Endpoints

### Game Endpoints

1. **Create a new game**
   - **URL**: `/games`
   - **Method**: `POST`
   - **Request Body**:
     ```json
     {
       "date": "2024-12-12",
       "gameType": "classic",
       "maxScore": 100,
       "hostId": 1
     }
     ```
   - **Response**:
     ```json
     {
       "id": 1,
       "date": "2024-12-12",
       "gameType": "classic",
       "maxScore": 100,
       "hostId": 1
     }
     ```

2. **Get a game by ID**
   - **URL**: `/games/{id}`
   - **Method**: `GET`
   - **Response**:
     ```json
     {
       "id": 1,
       "date": "2024-12-12",
       "gameType": "classic",
       "maxScore": 100,
       "hostId": 1
     }
     ```

3. **Update an existing game**
   - **URL**: `/games/{id}`
   - **Method**: `PUT`
   - **Request Body**:
     ```json
     {
       "date": "2024-12-13",
       "gameType": "rapid",
       "maxScore": 200,
       "hostId": 1
     }
     ```
   - **Response**:
     ```json
     {
       "id": 1,
       "date": "2024-12-13",
       "gameType": "rapid",
       "maxScore": 200,
       "hostId": 1
     }
     ```

4. **Delete a game**
   - **URL**: `/games/{id}`
   - **Method**: `DELETE`
   - **Response**: `204 No Content`

### Participation Endpoints

1. **Register a player's participation in a game**
   - **URL**: `/participations`
   - **Method**: `POST`
   - **Request Body**:
     ```json
     {
       "gameId": 1,
       "playerId": 1,
       "score": 150,
       "victory": true
     }
     ```
   - **Response**:
     ```json
     {
       "id": 1,
       "gameId": 1,
       "playerId": 1,
       "score": 150,
       "victory": true
     }
     ```

---

## Business Logic

### Game Logic
- The game service is responsible for creating, updating, retrieving, and deleting game sessions. Each game has a unique ID, a date, game type, maximum score, and a host player. 
- Once a game is finished, the participation service records player results, including score and victory status.

### Player Stats Update
- When a player wins a game, the player's score and level are updated through an external player management system. The level is incremented by 1 and the score is increased by 50 points each time the player wins a game.

---

## Database Structure

The database consists of the following tables:

### 1. **Game Table**
   - **id**: `BIGINT` (Primary Key, Auto Increment)
   - **date**: `DATE`
   - **game_type**: `VARCHAR(255)`
   - **max_score**: `INT`
   - **host_id**: `BIGINT` (Foreign Key referencing Player ID)

### 2. **Participation Table**
   - **id**: `BIGINT` (Primary Key, Auto Increment)
   - **game_id**: `BIGINT` (Foreign Key referencing Game ID)
   - **player_id**: `BIGINT` (Foreign Key referencing Player ID)
   - **score**: `INT`
   - **victory**: `BOOLEAN`

### Relationships:
- Each **Game** can have multiple **Participations**.
- Each **Participation** references a **Player**.

### Justification:
The schema ensures efficient tracking of player scores and game results. The **game_id** and **player_id** foreign keys enable seamless relations between games and player participations. The `score` and `victory` fields in the **Participation** table allow detailed tracking of performance per player.

---

## Player Management System

The **Player Management** system handles the players' data, including player profiles, total scores, levels, and friends. It interacts with the **Game Management** system to update player scores and levels.

### Player Endpoints

1. **Create a new player**
   - **URL**: `/players`
   - **Method**: `POST`
   - **Request Body**:
     ```json
     {
       "name": "John Doe",
       "username": "johnny123",
       "email": "john.doe@example.com",
       "level": 1,
       "total_points": 0
     }
     ```
   - **Response**:
     ```json
     {
       "id": 1,
       "name": "John Doe",
       "username": "johnny123",
       "email": "john.doe@example.com",
       "level": 1,
       "total_points": 0
     }
     ```

2. **Get player**
   - **URL**: `/players/{id}`
   - **Method**: `GET`
   - **Response**:
     ```json
     {
       "id": 1,
       "name": "John Doe",
       "username": "johnny123",
       "email": "john.doe@example.com",
       "level": 1,
       "total_points": 0
     }
     ```

3. **Edit player**
   - **URL**: `/players/{id}`
   - **Method**: `PUT`
   - **Response**:
     ```json
     {
       "id": 1,
       "name": "John Doeeeee",
       "username": "johnny1233333",
       "email": "john.doe123@example.com",
       "level": 1,
       "total_points": 0
     }
     ```

4. **Delete player**
   - **URL**: `/players/{id}`
   - **Method**: `DELETE`
   - **Response**: `Player deleted successfully`

5. **Update player stats (score and level)**
   - **URL**: `/players/{id}/stats`
   - **Method**: `PUT`
   - **Request Body**:
     ```json
     {
       "score": 50,
       "level": 2
     }
     ```
   - **Response**:
     ```json
     {
       "id": 1,
       "name": "John Doe",
       "username": "johnny123",
       "email": "john.doe@example.com",
       "level": 2,
       "total_points": 50
     }
     ```

### Friend Endpoints

1. **Add a friend**
   - **URL**: `/players/{id}/friends`
   - **Method**: `POST`
   - **Request Body**:
     ```json
     {
       "friendId": 2
     }
     ```
   - **Response**:
     ```json
     {
       "id": 2,
       "username": "ayoub"
     }
     ```

2. **Get the friends list**
   - **URL**: `/players/{id}/friends`
   - **Method**: `GET`
   - **Response**:
     ```json
     [
       {
         "id": 2,
         "username": "ayoub"
       },
       {
         "id": 3,
         "username": "test"
       }
     ]
     ```

3. **Delete friend**
   - **URL**: `/players/{id}/friends/{friendId}`
   - **Method**: `DELETE`
   - **Response**: `Friend removed successfully`

---

## Application Workflow

1. **Creating a Game**:
   - A game is created through a POST request to the `/games` endpoint. The system saves the game with its date, type, max score, and host information.

2. **Player Participation**:
   - Players participate in the game by sending their scores to the `/participations` endpoint. If the player wins, the game system updates the player's score and level.

3. **Updating Player Stats**:
   - The game service calls the player management system to update the player stats, incrementing the score by 50 points and increasing the level by 1 upon a victory.

4. **Player Profile and Stats**:
   - Players can view and update their profiles and stats via the Player Management system. The player's total score and level are updated whenever they participate and win a game.

5. **Managing Friends**:
   - Players can add, remove, and view their friends through the `/players/{id}/friends` endpoints. These endpoints allow players to maintain and manage their social connections within the game.

---

## Conclusion
This application allows for seamless management of games and players. By using RESTful APIs, we ensure that the system is modular, scalable, and easy to integrate with other services. The database design ensures that data is stored efficiently and maintains the integrity of game and player data.
