# Battleship

Software Engineering Project - BattleshipGame

Recreation of the popular board game Battleship with online connectability using java.

Created by Austin Ahne, Kevin Camp, Brian Eubanks, Job McCully, Josh McIlvoy

## Setup

### Importing jar files

Ensure that both ocsf.jar and mysql-connector-java-5.1.40-bin.jar are added to the project build path (right click project, build path->libraries->add jars->add ocsf and mysql jar files located within project directory).

### bsDBUser.sql

Creates a 'student' user.
Creates a 'studnet_space' database.

### bsDB.sql & bsDBwin.sql

Creates a 'user' table with 'id', 'username', and 'password' fields.

## Starting the Program

### Starting the Server

On Windows, use the bsServerWIN.bat file to start the Server.

```bash
bsServerWIN.bat
```
On OSX, use the bsServer.sh file to start the Server.

```bash
./bsServer
```
### Starting the Clients

On Windows, use the bsClientWIN.bat file to start the program.

```bash
bsClientWIN.bat
```

On Windows, use the bsClient.sh file to start the program.

```bash
bsClientWIN.bat
```

### Connecting to the Server

Make sure to click the Listen button on the server before trying to connect a client.

Once the server is listening, click Connect on the client to connect to the server. The Server URL defaults to localhost, but when connected to a different system the user must enter the IP address of the system the server is hosted on.

## Login

Once a client is connected, the user may choose to create an account, or login. Once the user logins with a valid account,  they are presented with a game screen. When two users successfully login, the game starts.

## GamePlay

### Placing Ships

The players begin by placing 4 ships. The BOTTOM half of the screen represents their own board. The ships are placed in the following order. Left clicking places a ship horizontally, right clicking places a ship vertically. Players may not place ships until an opponent has connected.

Ship 1: Length 2

Ship 2: Length 3

Ship 3: Length 3

Ship 4: Length 4

Once both players have placed all of their ships, the game will start, and the player who connected first will be able to make the first move.

### GamePlay

Players take turns attempting to sink all of their opponents hidden ships while hoping that their own ships are not sunk. The opponent's board is represented by the top half of the board while the player's board is on the bottom half. The winner is determined whenever a player has sunk all of his opponents ships or the oppposing player decideds to forfeit the game.

### EndGame

Once one of the players has sunken all of their their opponents ships or a player forfeits, the game is over.








