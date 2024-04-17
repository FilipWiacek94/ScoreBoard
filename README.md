# ScoreBoard Library

##### The application is a simple implementation of a football scoreboard. The names of many popular soccer club names were used for testing.

## Technical Information

The project was written in Java version 17. The project uses Maven to manage dependencies and build the application. To build the application, use the command:
```
//standard build
mvn clean install 

//build without tests
mvn clean install -DskipTests
```

jUnit version 4 was used as the framework for the tests.

The following external libraries were used in the project: 
1. Joda-Time --> A library to facilitate working with time, used to store the start time of a soccer match. 
2. Apache commons-lang3 --> The Pair object provided in this library that allows storing two values on a key-value basis was used. In this case, it was used to store the team-score value for a single match. 

### Architecture description 
The following objects are present in the application: 
1. ##### **FootballMatch** --> ValueObject representing a single match. This object is responsible for changing its state and provides public methods responsible for changing states such as the result of the match and whether the match is in progress or completed. When the object is created, validation of the input data is performed. 
2. ##### **FootballMatchRepository** --> The object responsible for directly calling methods that change the states of the FootballMatch object. Validation of parameters is also performed in it. This object is also responsible for storing matches in the database (in this case, it is a list of matches) and performing operations to create a score board.
3. ##### **FootballMatchProcessor** --> The object responsible for calling the methods of FootballMatchRepository. Provides an external interface that makes it easier for users to use the library. 
4. ##### **MatchStatus** -> Enum representing the state the match is in. At this point, it indicates whether the match is in progress or has been ended. 

### Clarification 
As you can see, in the tests, I do not use constants in all cases. This is a conscious decision, I believe that in the case where the string occurs in the test once or twice, moving it to a constant is not particularly important.

### TODO
1. Prepare encapsulation so that only the methods provided by FootballMatchProcessor are visible from the user level. 
2. Prepare validation to prevent one team from playing multiple matches simultaneously. 