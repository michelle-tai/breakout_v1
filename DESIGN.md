Game Design Doc
====

Design Document

Name: Michelle Tai (mrt36)

Role: Ideated, designed, and coded the individual project

This document is typically an update of a planning Design Document to describe the final, implemented, version of the code to provide a detailed roadmap for other developers to understand your project's design goals.

This file should be included in the doc folder of your project repository and contain the following information:

names of all people who worked on the project
each person's role in developing the project
what are the project's design goals, specifically what kinds of new features did you want to make easy to add
describe the high-level design of your project, focusing on the purpose and interaction of the core classes
what assumptions or decisions were made to simplify your project's design, especially those that affected adding required features
describe, in detail, how to add new features to your project, especially ones you were not able to complete by the deadline
Submission Guidelines
One version of this file should be completed as a team and any part of it can be used within your individual analysis as appropriate.
This file must be completed when the project's analysis is submitted.

## Design Goals
My basic design goals for my game were to:
* Have a paddle that the ball would be able to bounce off of based on collision calculations
* Have the ball bounce is an expected way for Breakout (i.e. if it's moving upward and hits a side of a brick, it won't start moving down, 
but continue to bounce upward until it reaches the top of the screen)
* Create bricks and have the ball bounce of them & clear the brick

My higher-level design goals were for my game to:
* Be designed in an atomic/organism-like way so that levels could easily be added or removed
* Stop the ball once the number of lives hits 0 and allow for a sort of transition between levels, which can be in the form of user's
keyboard input
* Create different colored blocks that took different numbers of hits to clear 
    * A light brown one (classic milk tea) that only takes 1 hit to clear
    * A light green one (matcha) that takes 5 hits to clear
    * And a light purple one (taro) that takes 15 hits to clear.  
Each brick's point value will equal the number of times it takes for the ball (a boba) to hit it until 
the brick clears. 
* Have 3 different power ups that are attached to some blocks and could easily be changed for each block
* Adding a special brick to add more variety to the game. This block, when clicked, would add points to your current score.

I wanted to make it easy to add more levels and more powerups in the game. 

## High-Level Design 
My project is split is a sort of hierarchy where the paddle and bricks extend the "Rectangle" class, and a level would have its
own Brick and Paddle objects. These 2 classes, in addition to the Ball class, were used in the levelChooser class since each level needs 
these 3 basic elements. This levelChooser class is then also used in the Main class–the levelChooser object contains all the information
about the level, including orientation of blocks, colors of blocks, and types of blocks, so that the Main class only needed to worry about displaying
the level and its contents.  
1) Ball and brick interactions:  
    * The ball figures out whether or not it has collided with a brick, and depending on which part of the brick it hit, the ball would change
    direction to mimic "bouncing" off the brick. The brick, which held information about the coordinates of its edges, was passed as an argument into 
    one of the ball's methods so that the ball could use the brick's information to calculate collisions. 
2) Ball and paddle interactions: 
    * Much like the ball and brick interactions, the ball detects when it collides with a paddle, which is passed as an argument into one of the ball's
    methods, and the ball bounces accordingly. 
3) Ball and wall interactions: 
    * The ball calculates whether or not it reaches the edge of the screen and changes direction accordingly.
In all, I had the ball do most of the collision calculations since it's the object that reacts most often based on collisions (by bouncing). However, the ball
returns a boolean about whether or not a collisions with a "Rectangle" has been detected, and the bricks can also update its hitcounter based on this boolean.  

My Ball objects would hold information about how and when to change direction based on shape intersections, the position of the ball, whether or not the ball "died" 
by falling past the paddle, and the initial position so that it can be reset. The Ball class implemented a Sprite interface, where a Sprite
would have information about its x and y coordinates and return it, or allow the coordinates to be updated. 

My Brick object would hold information about the position and edges of the brick, what number of collisions is needed to clear the brick, and increment the current hit count of the 
brick. The Brick manages and updates all this information because it applies solely to the brick and keeps implementation hidden from others. 

My Paddle object is very similar to the Brick objects, except instead of managing a hit count, the paddle can move its x position left or right depending
on the user's keyboard input (right arrow keys and left arrow keys). Since the paddle and brick were so similar, I chose to have them both extend the Rectangle class, 
which also made it easy to add the object themselves as nodes to the scene (in oppose to only being able to add Shape.Rectangle of the object if the classes did not 
extend the Rectangle class).

The levelChooser class was created so that, based on the level, a specific configuration of bricks could be "drawn" on the scene. For example, for level 2, there are 2 rows of
bricks, with the lower level of bricks needed 2 hits to clear and the top level only needing 1 to clear. The level manages its own ball, paddle, and bricks, since they
don't need to be passed between levels. The level also manages the text display of the score and number of lives left. The level packages each object type into its own group and returns
these groups to the Main class so that it can be added to the scene root node and rendered.

## Assumptions/Decisions Made to Simplify Project Design 
As stated in the previous section, I attempted to create a type of hierarchy so that the implementation and design choices of lower level objects, like bricks, were hidden from
higher-level objects, like the levelChooser, so that the higher-level objects could focus on it's own tasks and not how the lower level objects are supposed to behave (ex. like a ball
calculates its own directions, the level doesn't need take into account/care how the ball will move).

## How to Add New Features
#### Bricks
* I wasn't able to implement my plan of having a light brown brick (representing classic milk tea) that only takes 1 hit to clear,
 a light green one (matcha) that takes 5 hits to clear, and a light purple one (taro) that takes 15 hits to clear. To adjust this, I could have a
 parameter passed into the Brick constructor to tell it what type of brick it should be, which the sets the correct maxHit count. 
* Since I already had a basic layout of the brick taking 1, 2, or 3 hits, I think I could easily add an instance variable that holds what type of brick it
should be and add conditionals to set the corresponding brick count. 
* I could also extend the Brick class to have each type of brick become its own class, which would get rid of long conditional statements if, in the future, I decided
to add more than 3 brick types.
* To add more brick types to my code in the way I have it right now, I would have to add more if-statements at line 45 in the Brick class, with each
maxHitVal corresponding to a different color. This is not sustainable since a lot of if-statements would be added if someone wanted to add a lot of brick types. 
    
#### Layout/Levels                                                         
* I was able to create a basic grid layout, but didn't get to reading a layout from a .txt file. To do this, I would have the levelChooser have a method that takes in a file
name as an argument, parse the layout by using a Scanner (a 0 meaning a space, a 1 meaning the brick that takes 1 hit to clear, a 5 meaning a brick that takes 5 hits to clear, etc.), and store this
layout into a grid. Then, I would create a grid of Brick objects in which each index holds a corresponding brick type. At the end, I would add all the bricks to the brickGroup, which would later
be added to the scene's root node in the Main class. 
* I also was not able to pass the previous level's score to the next level: the score reset each time. To remedy this, I might save the current score when there are no blocks and initialize the next level's score
using this value.
* To add more text screens (like win or lose), I would probably make a new class for these screens, and depending on whether or not there are no more live or if theyve cleared all the blocks in the last level, 
I would clear the scene's root node in Main and add the winning or losing text to the node to be displayed (and somehow stop the step function).
* To add a basic level that looks like the ones I currently have, I would change line 151 in the Main class
 ```java
 if (code == KeyCode.DIGIT3 || code == KeyCode.DIGIT4 || code == KeyCode.DIGIT5 || code == KeyCode.DIGIT6 || code == KeyCode.DIGIT7 || code == KeyCode.DIGIT8 || code == KeyCode.DIGIT9) {
     currLevelNum = 3;
     setNewLevel();
 }
 ```
 to 
 ```java
 if (code == KeyCode.DIGIT3) {
     currLevelNum = 3;
     setNewLevel();
 } 
 if(code == KeyCode.DIGIT4 || code == KeyCode.DIGIT5 || code == KeyCode.DIGIT6 || code == KeyCode.DIGIT7 || code == KeyCode.DIGIT8 || code == KeyCode.DIGIT9))
       currLevelNum = 4;
         setNewLevel();
     } 
 ```
However, this isn't sustainable since each new level addition would require adding another if-statement. 

#### Power-ups
* I wasn't able to add power ups, but to add a power up that slows down the ball, I would first attach that power-up to a random block in the grid (so the blocks hold information about whether or not it
has a power up), and when the ball collides with this special brick, it would slow down its speed by decreasing it xDir and yDir values for a certain amount of time. I could also continue to extend the Brick class and 
make new classes for power-up bricks, which would make things a little easier. To check if the certain amount of time has
elapsed, the moment/elapsedTime value when the ball collides with this special brick is save, and upon every "step" in the main, the brick compares its initial time value with the current elapsedTime value.  
* To add power-ups, I would probably attach them to specific bricks, so I could extend the Brick class and make individual power-up bricks that, when cleared, gave you a certain power up

