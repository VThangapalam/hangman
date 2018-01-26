Steps to run the Hangman SpringBoot  web App
Server
1.	Download the hangman.jar  
2.	Verify that Java is installed on the system using the command
Java -version
3.	Make sure that no other process is running on 
Port 8093 (tomcat)
Port 27018 (embedded MongoDB)
Command: netstat –ltnp | grep –w ‘:8093’
4.	Run the application jar from the terminal
Java –jar hangman.jar
5.	For debugging ,testing or changing server config import the project to an IDE that support Jav
Client
Start playing the game through the following url:
http://localhost:8093/hangman.html



DB Verification 
- You can access the MongoDB embedded server using  the Robo 3T MonogDB client tool
	DB details:
	DB port : 27018
	DB name : hangmanDB
- Test hangmanDB: hangmanDBTest
- Can be downloaded from the following link : https://robomongo.org/
- Note: As an embeddedDb is used the DB is deleted on restart.

Implementation Details
-	This project is implemented using Spring Boot framework 
-	Server side implementation provides REST APIs to play the game
- Client side implemented using HTML and Javascript .
- Data is persisted in the Embedded Mongo DB instance.
- Junit and Mockito frameworks are used for testing



