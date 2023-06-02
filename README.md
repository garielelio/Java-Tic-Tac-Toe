# Tic Tac Toe Game

This program creates a Tic Tac Toe and Number Tic Tac Toe Game with 2 players

## Description

This program uses GUI, encapsulation, setters and getters, and OOP. The user can run Tic Tac Toe game from terminal
or with the GUI. With the GUI, the user can also choose game between Tic Tac Toe or Number Tic Tac Toe. The program
will let the user to play and it will determine the winning condition such as if the game is a tie or a player wins. If
the user enters an invalid location, the program will ask the user to enter the value again. The program allows the user
to save the game to a file. The winner for the tic tac toe is determined by who can connect 3 Xs or Os first. The game
is considered a tie if the board is full and no winner is found. The winner for the number tic tac toe is determined by
who can connect 3 digits that adds up to 15 or more no matter if the number connected is even or odd. The file for the saved
game is located inside the assets folder.

## Getting Started

### Dependencies

* The program requires to be run in a pc that has java downloaded
* The program requires terminal to play the non-GUI version of the tic tac toe game

### Executing program
* Note: Executing will vary for everyone   
       
* scioer start and scioer shell
* cd to folder A3
* To build the program: gradle build
* To run the program in terminal: java -cp build/classes/java/main tictactoe.TextUI
* To run the program for GUI: java -jar build/libs/A3.jar
```
gradle build
java -cp build/classes/java/main tictactoe.TextUI

or

java -jar build/libs/A3.jar
```
* Expected output:
```
across? (0 to quit)
2
down?
3
      
      
   X  

Player 2 please indicate where you would like to put your token.
across? (0 to quit)
```
## Limitations

* Load and Save player profile is not yet functional
* Load the game from a file is not yet functional

## Author Information

* Name: Gariel Mahwastu
* Email: gmahwast@uoguelph.ca

## Development History
* November 21
    * Finished building the terminal game
* November 23
    * Adding features to GUI
* November 30
    * Fixing errors and adding features to the GUI

## Acknowledgments

Inspiration, code snippets, etc.
* [exampleguiproject] (https://gitlab.socs.uoguelph.ca/examples/exampleguiproject.git)
* [awesome-readme](https://github.com/matiassingers/awesome-readme)
* [simple-readme] (https://gist.githubusercontent.com/DomPizzie/7a5ff55ffa9081f2de27c315f5018afc/raw/d59043abbb123089ad6602aba571121b71d91d7f/README-Template.md)


