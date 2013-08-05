DoxProject
==========
This project is an AI implementation to play the game Dots and Boxes, which can be found here: 
http://en.wikipedia.org/wiki/Dots_and_Boxes

The command line interface, which is used to setup the initial Dox board runs according to these specification:

Command line interface

We will run your program on the command line, e.g.
java Dox 3x3 A XXXX..X.XXX. AB.. 3 Y 1
for a Java program, or
Dox 3x3 A XXXX..X.XXX. AB.. 3 Y 1
for a C program.
Your program should read in seven parameters from the command line, separated by spaces:

3x3 — the number of rows (first) and columns (second) of dots, with an x inbetween.
A — the player whose turn it is, A or B
XXXX..X.XXX. — a string in which X indicates a line that has been drawn, and . indicates a space between two dots. The locations are indicated in the following order: horizontal locations, top to bottom, left to right, then vertical locations, left to right, top to bottom. For instance, with a 3 x 3 board, with asterisks indicating dots, the line locations are
*  0	*	1	*
6	 	8	 	10
*	2	*	3	*
7	 	9	 	11
*	4	*	5	*
AB.. — a string indicating which boxes belong to which player, A, B, or . meaning the box's walls are not complete. For instance, with a 3 x 3 board, with asterisks indicating dots, the box locations are
*	 	*	 	*
 	0	 	1
*	 	*	 	*
 	2	 	3	 
*	 	*	 	*
3 — a number indicating the maximum number of plies the algorithm should look ahead.
Y — Y indicates that alpha-beta pruning should be used, N means don't use alpha-beta
1 — indicates which board evaluation heuristic shold be used. 1 is a supplied simpleBoardEval method, and 2 is one that you write. 

