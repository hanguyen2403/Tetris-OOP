# Tetris
![logo](https://i.imgur.com/RyRjSbc.png)




# Table of content
1. Introduction
2. Game
3. UML-diagram
4. Features

## Introduction
This is our Tetris project for the Object-Oriented Programming course at HCMIU in semester 2 (2022-2023). In the project, our group will cover some of the Object-Oriented Programming (OOP) Concepts: Encapsulation, Inheritance, and Polymorphism and apply them to create an update version of Tetris with some new features to the game. We hope you enjoy it!
### Team Members
| Order | Name| Student ID| Contribution |
|--------------|-------|------|-------|
| 1 | Nguyen Khanh Ha | ITCSIU21004 | 100% |
| 2 | Huynh Lam Dang Khoa | ITCSIU21138 | 90% |
| 3 | Nguyen Binh Phuong Huy | ITCSIU21189 | 90%|
| 4 | Tran Thanh Nguyen | ITCSIU21093 | 95% |

### How to run
1. Clone this repository
```c
git clone https://github.com/hanguyen2403/Tetris-OOP.git
```
2. Open the project with IntelliJ IDEA or VSCode and check the file status
```c
git status
```
3. Run the project
4. Enjoy the game
## Game
#### About The Game
![logo](https://i.imgur.com/5kEXFAI.png)
- Language: [Java](https://www.java.com/en/)
- IDEs: [IntelliJ IDEA](https://www.jetbrains.com/idea/)
- Library: [JavaSwing](https://docs.oracle.com/javase/tutorial/uiswing/)
- Game Engine: [Java2D](https://docs.oracle.com/javase/7/docs/api/java/awt/Graphics2D.html)
- Sound: [JavaSound](https://docs.oracle.com/javase/7/docs/api/javax/sound/sampled/Clip.html)
#### Controls
| Key | Action |
|--------------|-------|
| UP | Rotate Block | 
| DOWN | Move Down|  
| LEFT | Move Left| 
| RIGHT | Move Right| 
| SPACE | Drop Immediately  | 
| C | Change Block | 

#### Blocks
| Block | Image |
|--------------|-------|
| Z block | ![block](https://i.imgur.com/yBiZVVU.png) | 
|	S block | ![block](https://i.imgur.com/pWnqmQL.png)|  
| 	L block | ![block](https://i.imgur.com/5EFKWED.png)| 
| J block | ![block](https://i.imgur.com/Bwui9kw.png)| 
| T block | ![block](https://i.imgur.com/zM1A99r.png)  | 
| O block | ![block](https://i.imgur.com/1DllBmW.png) |
| I block | ![block](https://i.imgur.com/8WEGRHK.png) | 

The blocks are controlled by the arrow keys and the space bar. The player can move and rotate the block by pressing the arrow keys. The player can drop block immediately by pressing the space bar. The player can change the block by pressing the C key.



## UML diagram
#### Block Diagram
![diagram](https://i.imgur.com/SGFIX0d.png)
#### Controls Diagram
![diagram](https://i.imgur.com/5r2gWVY.png)
#### GUI & Main Diagram
![diagram](https://i.imgur.com/spl7bCO.png)
#### Constant Diagram
![diagram](https://i.imgur.com/H27cZTO.png)


# Features
Unlike the original game, in this version, we decided to change something in the gameplay to make it a bit differ. We added some new features to the game, such as:

- The player can change block if they are not satisfied with the current block.
- The player can see the next three blocks to plan their next moves.
- The player can play again and again with infinite level.
- The falling speed of the block will gradually increase after each level to make the game more challenging.
