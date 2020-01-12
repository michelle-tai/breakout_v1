# Game Plan
## NAME: Michelle Tai (mrt36)


### Breakout Variant
I thought "Super Breakout" was interesting because of the positive and negative points 
you could get after breaking the bricks and having your paddle catch it. I like how the 
player had control over which points they could get, whether positive or negative, forcing
the player to think more about whether getting certain point drops was worth the risk of 
losing a life. 

### General Level Descriptions
I plan to build 3 levels. In diagrams, 0's indicate a space, 1's indicate a brick that breaks after 1 hit, 
5's indicate a brick that breaks after 5 hits, and X's to indicate a brick that breaks 
after 15 hits.<br>
**Level 1:** The level will be composed of mainly easy-to-break bricks in a normal order,
similar to (but not exactly) what's shown below:<br>
5 1 X 1 1  
1 5 1 5 1  
0 1 0 1 0  
1 0 1 0 1  

**Level 2:** There will be 1 more row in this level, and the rows will be closer to the paddle, 
making it hard to keep track of the ball. There will also be more 5- and 15-hit blocks. The level
may look something like this:<br>
5 5 5 5 5  
5 X 1 X 5  
0 1 X 1 0  
1 1 1 1 1  
1 0 1 0 1  

**Level 3:** There will be 1 more row in this level compared to the last, and the row closest to 
the paddle will no longer have spaces, but have 1- and 5-hit blocks, making it harder to keep 
track of the ball again. There will also be more 15-hit blocks. It may look something like this:<br>
X 5 5 5 1  
5 X 1 X 5  
1 5 X 5 X  
5 0 5 X 5  
1 1 5 5 1  
5 1 5 1 1  

### Bricks Ideas
So I want to make this game bubble-tea themed, so I want to have 3 brick types:  
* a light brown one (classic milk tea) that only takes 1 hit to clear
* a light green one (matcha) that takes 5 hits to clear
* and a light purple one (taro) that takes 15 hits to clear.  
Each brick's point value will equal the number of times it takes for the ball (a boba) to hit it until 
the brick clears. 

### Power Up Ideas
Some power up ideas I have are:<br>
* Making the ball move slower
* Giving an extra life
* Making extra balls appear on the screen

### Cheat Key Ideas
I will implement the required cheat keys, as well as some of my own:
* "L" adds lives to the player
* "R" resets the ball and paddle to the starting position
* "1-9": when the player presses a numeric key, clear the current level and jump to the level corresponding 
to the number pressed (or the highest one that exists)
* Clicking a certain spot on the screen gives you extra points

### Something Extra
My extra thing I would like to add is a special brick/icon that appears once maximum in each level, and it's 
point value decreases as time increases, but only takes 1 hit to clear. This is so that the player has to 
strategize, deciding whether they to prioritize clearing this special block to get more points at the risk of losing
lives, or playing it safe at the risk of losing points. 
