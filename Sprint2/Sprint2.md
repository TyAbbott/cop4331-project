# Sprint 2 Review
Sprint 2 Review for: Ty Abbott, Derek Goodwin, Matthew Klein, and Aleksandar Stoyanov
## Vision Statement
Man has been playing Tic-Tac-Toe for thousands of years.  The game has become bland and replaced by more engaging games.  The base concept is great and we want to build off of it to build the 21st century version, A Few in a Row.  Our game will be played on the player’s computer with options to play against another human locally, online, or against the computer.  The player’s will be able to select the size of the board and the target number of pieces they want to get in a row. There will also be options to save and load games, as well as have a replay function that stores player moves.

The idea of a Tic Tac Toe variant is not a unique idea.  The idea of getting n-in-a-row, in a grid has been thought of before.  Our project can be thought of as a digital representation of these ideas, rather than a unique game that has never been created before.  A listing of these games can be found here: https://en.wikipedia.org/wiki/Tic-tac-toe_variants

## User Stories
| Story                                                                                                                                 | Priority | Effort | Validation                                                                                             | Status|
|---------------------------------------------------------------------------------------------------------------------------------------|:--------:|--------|--------------------------------------------------------------------------------------------------------|-------|
|~As a user, I want to be able to select the grid size, so I can have different game modes.~                                                | 5        | 1      | When I select a grid size, the game will open with that grid size.                                 |DONE   |
|~As a user, I want to be able to select the win #, so I can have   different game modes.~                                                  | 5        | 1      | When I select the win #, the game ends when a color has that number in a row.                      |DONE   |
|~As a user, I want the game to recognize when the win condition is   met, so the game works.~                                              | 5        | 3      | The game will not work or be fun to play if it doesn’t recognize when a player wins                |DONE   |
| As a user, I want to be able to see when a game is won, so I know the   game is over.                                                     | 5        | 1      | When a game is over a window pops up saying the game is over.                                      |WORKING|
| As a user, I want to be able to see the current player’s turn so I   know which player’s turn it is.                                      | 5        | 1      | There is text that changes, depending on the player’s name, which indicates player turn            |WORKING|
| As a user, I want to be able to see player win records, so I can brag   to my friends.                                                    | 2        | 3      | When playing consecutive games in a row, game will display win   totals.                           |WORKING|
|~As a user, I want the game board to open up and take up most of my   screen, so it’s easier to play the game.~                            | 4        | 3      | It’s annoying to resize the game to fit the user’s screen.                                         |DONE   |
| As a user, I want the game board to scale to my screen and be   resizable, so the game can take up as much room as I want.                | 4        | 2      | It is important for user’s to be able to change the game window.                                   |REMOVE |
|~As a user, I want the ability to load and save my games, so I can   come back and play them later.~                                       | 3        | 5      | Sometimes games can take a while to complete, so it would be useful to come back later to finish.  |DONE   |
| As a user, I want the ability to load and save my replays so I can   store them to view later.                                            | 2        | 5      | It would be interesting and useful to review games for strategy.                                   |WORKING|
| As a user, I want the ability to play replays, so I can re-watch a   game I played.                                                       | 2        | 2      | Storing replays is not useful if you cannot view them.                                             |WORKING|
|~As a user, I want the ability to play against another player locally   on the same machine, so I can play with my friends at my computer.~| 4        | 1      | The game would not be fun if you couldn’t play with anyone.                                        |DONE   |
| As a user, I want the ability to play against another player online,   so I can play my friends at a different computer.                  | 2        | 1      | Sometimes your friend isn’t at the same computer and you still want to play.                       |OPEN   |
| As a user, I want the ability to play against a computer, so I can   play when my friends aren’t available.                               | 3        | 13     | Sometimes you want to test your ability against somebody else.                                     |WORKING|
| As a user, I want the ability to have different computer   difficulties, so I can play against one that matches my ability.               | 1        | 13     | Player’s skill varies so having different computer difficulties can match player skill.            |OPEN   |
| As a user, I want the game to be displayed in English, French, and   Spanish, so that more people can play.                               | 1        | 8      | Our game can be played by anyone, but it’s more difficult to play if   it’s not in your language.  |OPEN   |
|~As a user, I want a start screen so I can access the different menus   to start a new game, load saves, and view other options.~          | 2        | 5      | When you open the game, you might want do something other than play.                               |DONE   |
|~As a user, I want the board to clear when the game is over, so I can   play a new game.~                                                  | 3        | 3      | It’s annoying to restart the game every time a game is played.                                     |DONE   |
| As a user, I want an option to rematch when a game is over, so I can   play again.                                                        | 3        | 1      | When a player wins, a window pops up with a button that allows   rematch                           |WORKING|
| As a user, I want the option to change board and piece design so I   can express myself.                                                  | 1        | 5      | Different people have different preferences.                                                       |OPEN   |
| As a user, I want the pieces to appear with an animation, so it looks   more realistic.                                                   | 1        | 2      | It will make the game look better.                                                                 |OPEN   |
| As a user, I want sound so the game is more interactive.                                                                                  | 1        | 3      | Most games have sound, and sound makes games more interactive.                                     |OPEN   |
| As a user, I want the game to highlight the winning pieces so I can   see the winning move.                                               | 2        | 1      | In big games it is difficult to see winning pieces.                                                |WORKING|
|~As a user, I want the game board to draw without indentations and   mis-texturing so the game looks nice.~                                | 4        | 3      | The game design shouldn’t take away from the gameplay and be   distracting.                        |DONE   |
|~As a user, I want the pieces to be placed where the mouse is clicked,   so accurate moves are made.~                                      | 4        | 5      | It’s annoying and would destroy the integrity of the game if pieces don’t go where a player wants. |DONE   |
|~As a user, I want the game to be an executable so I can run it easily~                                                                    | 3        | 3      | The game is meant to be played by people without technical   experience.                           |DONE   |
## Product Backlog & Sprint Backlog
[Product Backlog](https://github.com/TyAbbott/cop4331-project/blob/master/Sprint2/ProjectBacklog.pdf)

[Google Spreadsheets Version](https://docs.google.com/spreadsheets/d/1oetAO0jabmfB-8eYq7Jjqy2rfwcwWEucXJN0-8XoXZc/edit?usp=sharing)

## Burndown Chart
[Burndown Chart](https://github.com/TyAbbott/cop4331-project/blob/master/Sprint2/Sprint2burndown.pdf)

## Requirements
[Requirements](https://github.com/TyAbbott/cop4331-project/blob/master/Sprint2/Requirements.pdf)

## UML Diagrams
[Class Diagram](https://github.com/TyAbbott/cop4331-project/blob/master/Sprint2/AFewInARowUMLDiagram.pdf)

[Architecture Diagram](https://github.com/TyAbbott/cop4331-project/blob/master/Sprint2/ArchitectureUMLdiagram.png)

## Link to Code
[Game Logic](https://github.com/TyAbbott/cop4331-project/blob/master/Game_Logic.java)

[Load/Save Game](https://github.com/TyAbbott/cop4331-project/blob/master/Load_Save_Game.java)

[Game Frontend](https://github.com/TyAbbott/cop4331-project/blob/master/AFewInARow.java)

## Tests
[Tests](https://github.com/TyAbbott/cop4331-project/blob/master/Sprint2/ProjectTesting.pdf)

## Demonstration
* [Download JAR](https://github.com/TyAbbott/cop4331-project/blob/master/aFewInARow.jar) 
* Run JAR File, and play the game
