# COMP2013 Developing Maintainable Software - Breakout

**Name**: Praket Chavan (psypc1)

**Student Number**: 20302916

**Email**: psypc1@nottingham.ac.uk

Tested on Windows, Maven Build Tool, Java 
16, 
JavaFX 16

## How to run the code

To run the game for the first time on your machine in Intellij run the 
following command:

    mvn clean javafx:run

To run the game and preserve the high score list:
    
    mvn javafx:run

## JavaDocs  

The JavaDocs html pages are stored in the javadocs directory, which is 
stored in the project root directory

## Maintenance of the game

- Refactored the code to follow Bob's Concise coding convention

- Restructured the classes and put them in appropriate packages to improve 
  the code structure

- Encapsulated member variables wherever possible.


## Changes made to the game code

- Add JavaDocs for all the classes and methods to provide documentation for
  the code


- Migrated the code from Java Swing to JavaFX


- Split the GameFrame class into the GameView.fxml GameController and GameModel
  class to
  improve code readability and follow the single responsibility principle.


- Converted DebugConsole and DebugPanel class to DebugView.fxml
  and DebugController, to adhere to the MVC pattern


- Split the code of Brick, Ball and Paddle to their
  respective model, view and controller classes to adhere to the MVC pattern.
  The original class act as a wrapper class used to instantiate the MVC
  classes together.

- Removed the code for the drawing and controlling the pause menu of the
  game from the GameFrame class and created new class to handle the creation
  and user inputs for the pause menu (PauseMenuController and PauseMenuView.
  fxml)

- Moved the collision detection logic from the Wall class to the new
  ImpactHandler class to improve code structure and maintainability


- Improved code abstraction by introducing interfaces for common properties 
  of sprites in the util package


- Created new playable levels

## Additions made to the Game

- Added a welcome screen for the game.


- I added an option for the player to select the game theme which changes
  the color of the sprites as well as the color of the background of the game


- Added a permanent high score list that stores the score of the players
  that play the game. The high score list can be viewed at the start screen
  or at the end of the game. (However if you run `mvn clean` command then 
  the high score list is wiped out / reset because the file is stored in the 
  target directory of the project)


- Added game object sprites for the ball, paddle and bricks


- BrickFactory was added to remove the creation logic from the user code,  
  introduce abstraction, and therefore improve maintainability.


- To simplify code structure and divide responsibilities, I used the MVC
  pattern for all the game sprites and game screens.


- Added power ups that are randomly dropped by bricks to increase fun


- Added sounds and music to the game to make it more enjoyable and immersive

## Added Classes
- GameModel
- GameController
- DebugController
- PauseMenuController
- ImpactHandler


## Modified Java Classes

- GameFrame
- GameBoard  &#8594; GameController
- Wall  &#8594; GameModel
- Brick 
- Ball
- Paddle
- Ball1  &#8594; RubberBall
- Brick1  &#8594; ClayBrick
- Brick2  &#8594; SteelBrick
- Brick3  &#8594; UnbreakableBrick

