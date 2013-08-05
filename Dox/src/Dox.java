// Dox.java 

import java.util.*;

public class Dox
{
    public static int numRowsP, numColsP;
    public static String whoseTurnP;
    public static String linesP;
    public static String boxesP;
    public static int miniMaxPliesP;
    public static boolean useAlphaBetaP;
    public static int evaluationFunctionP;

    public static void main(String[] args)
    {
                // Parse and display command line arguments.  Samples:
                // 3x3 A XXXX..X.XXX. AB.. 3 Y 1
                // 5x5 B XXX....XXXX.XXXXXXXX.XXXX.XX.XXXX.XX.X.X ........AAA.AAAB 3 Y 1
                // 6x3 A X....XX.XXX.X..XX...XXX.... ......A.A. 3 Y 1
        checkArguments(args);
        System.out.println("  rows and cols: " + numRowsP + " x " + numColsP);
        System.out.println("     whose Turn: " + whoseTurnP);
        System.out.println("          lines: " + linesP);
        System.out.println("          boxes: " + boxesP);
        System.out.println("  minimax plies: " + miniMaxPliesP);
        System.out.println(" use alpha-beta: " + useAlphaBetaP);
        System.out.println("evaluation func: " + evaluationFunctionP);
        System.out.println("");

        int move;
        Dox dox = new Dox(numRowsP, numColsP, linesP, boxesP,
                                        miniMaxPliesP, useAlphaBetaP, evaluationFunctionP);
        
        
        //long avgTime = 0;
        //for (int x = 0; x < 100; x++)
        //{
        move = 0;
        dox = new Dox(numRowsP, numColsP, linesP, boxesP,
                                        miniMaxPliesP, useAlphaBetaP, evaluationFunctionP);

        // With this block of code, the computer chooses a single move and
        // that's it.  This matches the assignment.
        System.out.println(dox.board + "\n");
        long startTime = System.nanoTime();
        move = dox.chooseMove(whoseTurnP);
        long elapsedTime = System.nanoTime() - startTime;
        System.out.println("The computer's move is " + move);
        System.out.format("Elapsed time: %,d%n", elapsedTime);
        dox.board = dox.board.applyMove(move, whoseTurnP);
        System.out.println(dox.board);
        System.out.println("Computer gets another move: " + dox.board.anotherMove); 
        //avgTime = avgTime + elapsedTime;
        //}
        
        //System.out.format("Average Time(1000 cycles): %,d%n", avgTime/100);
        
        /*
        // With this block of code, the computer and human play interactively.
                Scanner scanner = new Scanner(System.in);
                boolean isComputersTurn = true;   // the computer always starts
                String computersLetter = whoseTurnP;
                String humansLetter = whoseTurnP.equals("A")? "B" : "A";
        while (!dox.board.gameIsOver())
        {
                        System.out.println(dox.board + "\n");
                        if (isComputersTurn)
                        {
                                move = dox.chooseMove(computersLetter);
                                System.out.println("The computer's move is " + move);
                        dox.board = dox.board.applyMove(move, computersLetter);
                                if (dox.board.anotherMove)
                                        System.out.println("The computer takes a square and gets another move.");
                                else
                                        isComputersTurn = false;
                        }
                        else
                        {
                                System.out.println("What's your move?");
                                move = scanner.nextInt();
                                DoxBoard newBoard = dox.board.applyMove(move, humansLetter);
                                dox.board = newBoard;
                                if (dox.board.anotherMove)
                                        System.out.println("The human takes a square and gets another move.");
                                else
                                        isComputersTurn = true;
                        }
                }
                System.out.println("Final position:\n" + dox.board);
                System.out.println("The score is " + dox.board.numBoxesOwnedBy("A") +
                                " points for A, and " + dox.board.numBoxesOwnedBy("B") +
                                " points for B.");
        */
        
        //Used to run simulation to play against different computer vs. computer simulation
        /*
        Dox dox2 = new Dox(numRowsP, numColsP, linesP, boxesP, 1, true, 2);
        boolean isComputersTurn = true;   // the computer always starts
        String computersLetter = whoseTurnP;
        String humansLetter = whoseTurnP.equals("A")? "B" : "A";
        
        while (!dox.board.gameIsOver())
        {
                System.out.println(dox.board + "\n");
                if (isComputersTurn)
                {
                        move = dox.chooseMove(computersLetter);
                        System.out.println("The computer's move is " + move);
                        dox.board = dox.board.applyMove(move, computersLetter);
                        dox2.board = dox.board;
                        if (dox.board.anotherMove)
                                System.out.println("Computer 1 takes a square and gets another move.");
                        else
                                isComputersTurn = false;
                }
                else
                {
                       // System.out.println("What's your move?");
                        //move = scanner.nextInt();
                        move = dox2.chooseMove(humansLetter);
                        dox2.board = dox2.board.applyMove(move, humansLetter);
                        dox.board = dox2.board;
                        
                        if (dox.board.anotherMove)
                                System.out.println("Computer 2 takes a square and gets another move.");
                        else
                                isComputersTurn = true;
                }
        }
        System.out.println("Final position:\n" + dox.board);
        System.out.println("The score is " + dox.board.numBoxesOwnedBy("A") +
                        " points for A, and " + dox.board.numBoxesOwnedBy("B") +
                        " points for B.");    
        */
        
        } 

        private int numRows;
        private int numCols;
        private int levels;
        private boolean useAB;
        private int evalFunc;
        private DoxBoard board;

        public Dox(int r, int c, String lines, String boxes,
                                int levels, boolean useAB, int evalFunc)
        {
                numRows = r;
                numCols = c;
                this.levels = levels;
                this.useAB = useAB;
                this.evalFunc = evalFunc;
                board = new DoxBoard(numRows, numCols, lines, boxes);
        }


        public int chooseMove(String whoseTurn)
        {
                List<Integer> validMoves = board.validMoves();
                System.out.println("validMoves: " + validMoves);

                int bestMove = -1;
                double bestEval = -99999999;
                for (int move : validMoves)
                {
                        DoxBoard newBoard = board.applyMove(move, whoseTurn);
                       
                        double eval;
                        
                        if (useAB == false) eval = maxValue(levels, newBoard, whoseTurn);
                        else eval = maxValueAB(levels , newBoard, whoseTurn, Integer.MIN_VALUE, Integer.MAX_VALUE);

                        System.out.println("eval: " + eval);
                        if (eval > bestEval)
                        {
                                bestMove = move;
                                bestEval = eval;
                        } 
                }
                System.out.println("bestEval: " + bestEval);

                return bestMove;
        }
        
        public double maxValueAB(int depth, DoxBoard board, String whoseTurn, double a, double b)
        {
            double v = Double.MIN_VALUE;
            
            if (depth == 0 || board.gameIsOver())
            {
                if (evalFunc == 2)return customBoardEval(board, whoseTurn);
                else if (evalFunc == 1) return simpleBoardEval(board, whoseTurn);       
            }
            
            for (int move: board.validMoves())
            {
                DoxBoard newBoard = board.applyMove(move, whoseTurn);               
                double eval;
              
                String otherTurn = whoseTurn.equals("A")? "B": "A";
                if (newBoard.anotherMove == true) eval = maxValueAB(depth - 1, newBoard, whoseTurn, a, b);
                else eval = minValueAB(depth - 1, newBoard, otherTurn, a, b);
                if (v < eval)
                {
                    v = eval;
                }
                
                if (v >= b) return v;
                a = (a > v ? a : v);
            }
            
            return v;
        }
        
        public double minValueAB(int depth, DoxBoard board, String whoseTurn, double a, double b)
        {
            double v = Double.MAX_VALUE;
            
            if (depth == 0 || board.gameIsOver())
            {
                if (evalFunc == 2)return customBoardEval(board, whoseTurn);
                else if (evalFunc == 1) return simpleBoardEval(board, whoseTurn);           
            }
            
            for (int move: board.validMoves())
            {
                DoxBoard newBoard = board.applyMove(move, whoseTurn);                            
                double eval;         
                
                String otherTurn = whoseTurn.equals("A")? "B": "A";
                if (newBoard.anotherMove == true) eval = minValueAB(depth - 1, newBoard, whoseTurn, a, b);
                else eval = maxValueAB(depth - 1, newBoard, otherTurn, a, b);
              
                if (v > eval)
                {
                    v = eval;
                }
                
                if (v <= a) return v;
                b = (b < v ? b : v);
            }
            
            return v;
        }
        
        public double maxValue(int depth, DoxBoard board, String whoseTurn)
        {
            double v = Double.MIN_VALUE;
            
            if (depth == 0 || board.gameIsOver())
            {
                if (evalFunc == 2)return customBoardEval(board, whoseTurn);
                else if (evalFunc == 1) return simpleBoardEval(board, whoseTurn);        
            }
            
            for (int move: board.validMoves())
            {
                DoxBoard newBoard = board.applyMove(move, whoseTurn); 
                double eval;
                
                String otherTurn = whoseTurn.equals("A")? "B": "A";
                if (newBoard.anotherMove == true) eval = maxValue(depth - 1, newBoard, whoseTurn);
                else eval = minValue(depth - 1, newBoard, otherTurn);
              
                if (v < eval)
                {
                    v = eval;
                }
            }
            
            return v;
        }
        
        public double minValue(int depth, DoxBoard board,String whoseTurn)
        {
            double v = Double.MAX_VALUE;
            
            if (depth == 0 || board.gameIsOver())
            {
                if (evalFunc == 2)return customBoardEval(board, whoseTurn);
                else if (evalFunc == 1) return simpleBoardEval(board, whoseTurn);        
            }                     
            
            // if (depth == 0 || board.gameIsOver() && evalFunc == 1) return simpleBoardEval(board, whoseTurn);
            // else if (depth == 0 || board.gameIsOver() && evalFunc == 2)

            for (int move: board.validMoves())
            {
                DoxBoard newBoard = board.applyMove(move, whoseTurn);           
                double eval;
                
                String otherTurn = whoseTurn.equals("A")? "B": "A";
                if (newBoard.anotherMove == true) eval = minValue(depth - 1, newBoard, whoseTurn);
                else eval = maxValue(depth - 1, newBoard, otherTurn);
              
                if (v > eval)
                {
                    v = eval;
                }
            }
            
            return v;
        }

        // This simple approach returns the number of boxes max has
        // minus the number of boxes the opponent has.
        private int simpleBoardEval(DoxBoard b, String max)
        {
            String min = max.equals("A")? "B": "A";
            return b.numBoxesOwnedBy(max) - b.numBoxesOwnedBy(min);
        }
        
        private double customBoardEval(DoxBoard b, String max)
        {
            String min = max.equals("A")? "B": "A";
            return (2 * b.numBoxesOwnedBy(max)) - (2 * b.numBoxesOwnedBy(min)) + (.75 * b.threeSides()) - (.5 * b.twoSides());
        }
        

    // checkArguments parses the command line.
    private static void checkArguments(String[] args)
    {
                if (args.length < 6)
                        printUsageAndExit("number of parameters");
                try
                {
                        String[] parms = args[0].split("x");
                        if (parms.length != 2)
                                printUsageAndExit("first parm not in RxC format");
                        numRowsP = Integer.decode(parms[0]).intValue();
                        numColsP = Integer.decode(parms[1]).intValue();

                        if (args[1].equals("A") || args[1].equals("B"))
                        {
                                whoseTurnP = args[1];
                        }
                        else
                                printUsageAndExit("second parm should be A or B, found " +
                                                                "<" + args[1] + ">");

                        int numLinePlaces = numRowsP * (numColsP - 1) + numColsP * (numRowsP - 1);
                        if (args[2].length() != numLinePlaces)
                                printUsageAndExit("third parm should have length " + numLinePlaces +
                                                                ", found " + args[2].length());
                        if (args[2].matches("\\A[X\\.]*\\z"))
                                linesP = args[2];
                        else
                                printUsageAndExit("third parm must consist only of X and .");

                        int numBoxes = (numRowsP - 1) * (numColsP - 1);
                        if (args[3].length() != numBoxes)
                                printUsageAndExit("fourth parm should have length " + numBoxes +
                                                                ", found " + args[3].length());
                        if (args[3].matches("\\A[AB\\.]*\\z"))
                                boxesP = args[3];
                        else
                                printUsageAndExit("fourth parm must consist only of A and B and .");

                        miniMaxPliesP = Integer.decode(args[4]).intValue();

                        if (args[5].equals("Y"))
                                useAlphaBetaP = true;
                        else if (args[5].equals("N"))
                                useAlphaBetaP = false;
                        else
                                printUsageAndExit("sixth parm must be Y or N");

                        if (args[6].equals("1"))
                                evaluationFunctionP = 1;
                        else if (args[6].equals("2"))
                                evaluationFunctionP = 2;
                        else
                                printUsageAndExit("seventh parm must be 1 or 2");
                }
                catch (Exception e)
                {
                        printUsageAndExit(e.toString());
                }
    }

    public static void printUsageAndExit(String message)
    {
                System.out.println(
                        "Run Dox with 6 command line arguments.\n" +
                        "  rows x cols - e.g. 4x5\n" +
                        "  player to move - A or B\n" +
                        "  already drawn lines - horizontal, then vertical, e.g. XX...XX....X\n" +
                        "  box owners - left to right, top to bottom, e.g. A..B\n" +
                        "  number of plies for minimax\n" +
                        "  Y to use alpha-beta pruning, N otherwise.\n" +
                        "\n" +
                        "Your error message is: " + message);
                System.exit(0);
        }
}
