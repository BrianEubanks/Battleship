# Battleship

Software Engineering Project.
Austin Ahne, Kevin Camp, Brian Eubanks, Job McCully, Josh McIlvoy

## Setup

### Starting the SQL Server

Make sure the SQL server is started.
On Windows...

```bash
C:\xampp\mysql_start.bat
```

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

Once the server is listening, click Connect on the client to connect to the server.

## Login

Once a client is connected, the user may choose to create an account, or login. Once the user logins with a valid account,  they are presented with a game screen. When two users successfully login, the game starts.

## GamePlay

### Placing Ships

The players begin by placing 4 ships. The BOTTOM half of the screen represents their own board. The ships are placed in the following order. Left clicking places a ship horizontally, right clicking places a ship vertically

Ship 1: Length 2

Ship 2: Length 3

Ship 3: Length 3

Ship 4: Length 4

Once both players have placed all of their ships, the game play begins.

### GamePlay

Players alternate turns attemting to guess where their opponents ships are hidden. The TOP half of the screen represents the opponents board.

### EndGame

Once one of the players has sunken their opponents ships, the game is over








