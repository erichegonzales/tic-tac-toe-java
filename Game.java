import java.util.Scanner;
/**
 * Represents a Tic-tac-toe game.
 *
 * @version CMPU-102 special edition
 * @author Eriche Gonzales
 * @date April 1, 2022
 */
public class Game
{
    /**
     * represents the game board as matrix of player symbols
     */
    static char[][] board;

    /**
     * represents board size, which will be a boardSize x boardSize matrix
     */
    static int boardSize;

    /**
     * holds references to the game's players
     */
    static APlayer[] players;

    /**
     * the character to be used to represent a blank space on the board (' ')
     */
    static char SYMBOL_BLANK = ' ';

    /**
     * the character to be used to represent a cpu move on the board ('O')
     */
    static char SYMBOL_CPU = 'O';

    /**
     * the character to be used to represent a human move on the board ('X')
     */
    static char SYMBOL_HUMAN = 'X';
    
    /**
     * represents the game stats
     */
    static GameStats stats;

    /**
     * Constructor for objects of class TicGame.
     */
    public Game(int boardSize) {
        this.board = board;
        this.boardSize = boardSize;
        this.SYMBOL_BLANK = SYMBOL_BLANK;
        this.SYMBOL_CPU = SYMBOL_CPU;
        this.SYMBOL_HUMAN = SYMBOL_HUMAN;

        board = new char[boardSize][boardSize];

        // creates a 1D matrix to represent the two players
        // e.g. players[0] = new HumanPlayer(this,'X');
        players = new APlayer[2];
        stats = new GameStats();

        // places a blank symbol in each element of the matrix
        for (int i=0; i < boardSize; i++) {
            for (int j=0; j < boardSize; j++) {
                board[i][j] = SYMBOL_BLANK;
            }
        }
    }

    /**
     * Returns the game board size.
     * @return the game board size
     */
    public int getBoardSize() {
        return boardSize;
    }

    /**
     * Resets the game state so we can play again.
     */
    protected void resetGame() {
        // makes all the spaces blank again
        for (int i=0; i < boardSize; i++) {
            for (int j=0; j < boardSize; j++) {
                board[i][j] = SYMBOL_BLANK;
            }
        }

        System.out.println("---------- NEW GAME ----------");
        System.out.println(toString());
    }

    /**
     * Validates a potential move. Returns 'V' if the move is valid, or a different character indicating the reason why the move is invalid.
     * @param move - the move to be validated.
     * @return 'V' is move is valid, 'R' if it specifies an invalid row, 'C' if it specifies an invalid column, or 'O' if it refers to an already-occupied position.
     */
    static public char isValidMove(Move move) {
        if ((move.row > boardSize-1) || (move.row < 0)) {
            System.out.println("Invalid row " + (char) (move.row+65) + ". Must be between A and " + (char) (boardSize+64) + ".");
            return 'R';
        }
        else if ((move.col > boardSize-1) || (move.col < 0)) {
            System.out.println("Invalid column " + (char) (move.col+49) + ". Must be between 1 and " + boardSize + ".");
            return 'C';
        }
        else if (board[move.row][move.col] != SYMBOL_BLANK) {
            System.out.println("Invalid move: position already taken.");
            return 'O';
        }

        return 'V';
    }

    /**
     * Executes the move passed as an argument. If the move is invalid, it returns false.
     * @param move - the move to be executed
     * @param symbol - the symbol of the player who is making the move
     * @return true if the move was successfully executed
     */
    static protected boolean executeMove(Move move, char symbol) {
        if (isValidMove(move) == 'V') {
            board[move.row][move.col] = symbol;
            return true;
        }

        return false;
    }

    /**
     * A method that analyzes the board to determine the current game state, which is then returned as a character. A game is over if either player has completed a row, a line, or a diagonal. Moreover, a game is also over if the board is full, even if no player completed a row, line, or diagonal. That indicates a tie situation.
     * @return A character indicating the game state: '?' if the game isn't over yet, 'T' if the game is over and tied, or, if a player won, the winning player's symbol ('X' or 'O').
     */
    public char getGameStatus() {
        // double loop that checks if all the indices in a row are complete
        for (int i=0; i < boardSize; i++) {
            for (int j=0; j < boardSize; j++) {
                // check which player won
                if (j==boardSize-1) {
                    if (board[i][j] == SYMBOL_HUMAN) return 'X';
                    else return 'O';
                }
                
                // if any symbol is blank in a row, move on to the next row
                if (board[i][j] == SYMBOL_BLANK) break;

                // check if the current symbol matches the symbol next to it
                if (board[i][j] != board[i][j+1]) break;
            }
        }

        // double loop that checks if all the indices in a column are complete
        for (int i=0; i < boardSize; i++) { 
            for (int j=0; j < boardSize; j++) {
                // check which player won
                if (j==boardSize-1) {
                    if (board[j][i] == SYMBOL_HUMAN) return 'X';
                    else return 'O';
                }
                
                // if any symbol is blank in a column, move on to the next column
                if (board[j][i] == SYMBOL_BLANK) break;

                // check if the current symbol matches the symbol below to it
                if (board[j][i] != board[j+1][i]) break;
            }
        }
        
        // loop that checks if a diagonal starting from the left is complete
        for (int i=0; i < boardSize; i++) { 
            // if the first symbol is blank, game is not finished
            if (board[i][i] == SYMBOL_BLANK) break;
            
            // check if the current symbol matches the first symbol in the diagonal
            if (board[i][i] != board[0][0]) break; 
            
            // check which player won
            if (i==boardSize-1) {
                if (board[i][i] == SYMBOL_HUMAN) return 'X';
                else return 'O';
            }
        }

        // loop that checks if a diagonal starting from the right is complete
        for (int i=boardSize-1; i >= 0; i--) { 
            // if the first symbol is blank, game is not finished
            if (board[boardSize-i-1][i] == SYMBOL_BLANK) break;
    
            // check if the current symbol matches the first symbol in the diagonal
            if (board[boardSize-i-1][i] != board[0][boardSize-1]) break; 
            
            // check which player won
            if (i==0) {
                if (board[boardSize-i-1][i] == SYMBOL_HUMAN) return 'X';
                else return 'O';
            }
        }

        // double loop that checks if the board is full without a winner
        for (int i=0; i < boardSize; i++) {
            for (int j=0; j < boardSize; j++) {
                if (board[i][j] == SYMBOL_BLANK) return '?';
            }
        }

        return 'T';
    }

    /**
     * Creates a textual representation of the game board.
     * 
     *    1   2   3
     * A  X | O | O 
     *   ---|---|---
     * B    | X |   
     *   ---|---|---
     * C  O |   | X 
     *
     * @return A String representing the game board in the aforementioned format.
     */
    @Override
    public String toString() {
        String gameBoard = "";

        // creates the first row of 1 2 3 ...
        for (int i=0; i < boardSize; i++) {
            if (i==0) gameBoard += "    " + (i+1) + " ";
            else gameBoard += "  " + (i+1) + " ";
        }

        for (int i=0; i < boardSize; i++) {
            // typecasts int to characters to use the alphabetical system
            char row = (char) (65+i);

            // creates the first column of A B C ...
            gameBoard+= "\n " + row + " ";

            // creates the spaces and space for the symbols
            for (int j=0; j < boardSize; j++) {
                if (j==boardSize-1) gameBoard+= " " + board[i][j] + "\n";
                else gameBoard+= " " + board[i][j] + " |";
            }

            // creates the dividers
            if (i!=boardSize-1) {
                for (int k=0; k < boardSize; k++) {
                    if (k==0) gameBoard+= "   ---";
                    else gameBoard+= "|---";
                }
            }
        }
        return gameBoard;
    }

    /**
     * Plays a single game of Tic-tac-toe by having players pick moves in turn. The first player to play is choosen uniformly at random.
     * @return A character representing the game's result: 'H' if the human player won, 'C' if the CPU won, 'T' if there was a tie, or 'Q' if the human quit the game.
     */
    public char playSingleGame() {
        resetGame();

        double prob = (Math.random());
        boolean humanFirstPlayer = false;
        boolean gameOver = true;

        if (prob <= 0.5)
            humanFirstPlayer = true;

        while(gameOver) {
            // Human move first if randomly chosen
            if (humanFirstPlayer) {
                Move humanMove = players[0].pickMove();
                
                // if human quits, go to main method to return the game stats
                if (humanMove.row == -1 && humanMove.col == -1) return 'Q';
                
                executeMove(humanMove, players[0].symbol);
                System.out.println(toString());
                humanFirstPlayer = false;
                if (getGameStatus() != '?') break;
            }

            // Cpu move first
            Move cpuMove = players[1].pickMove();
            
            // if valid, executes move
            executeMove(cpuMove, players[1].symbol);
            System.out.println(toString());
            if (getGameStatus() != '?') break;  
            
            // if human quits, go to main method to return the game stats
            Move humanMove = players[0].pickMove();
            if (humanMove.row == -1 && humanMove.col == -1) return 'Q';
            
            // Human move second
            executeMove(humanMove, players[0].symbol);
            System.out.println(toString());
            if (getGameStatus() != '?') break;
        }

        if (getGameStatus() == 'T') {
            System.out.println("Oh, a tie! Try again?");
            stats.recordTie();
            return 'T';
        }
        else if (getGameStatus() == 'X') {
            System.out.println("Congratulations, you won! Play again?");
            stats.recordWin();
            return 'H';
        }
        else if (getGameStatus() == 'O') {
            System.out.println("You lost! Try again?");
            stats.recordLoss();
            return 'C';
        }
        else return 'Q';
    }

    /**
     * Runs consecutive Tic-tac-toe games until the user gets tired and quits. When the user quits, the win-loss-tie statistics are printed.
     * @param args The first argument represents the desired game board size, which should be an integer in [1,9]. If the provided board size does not comply with these rules or if no argument is provided, a default game board size of 3 x 3 will be used.
     */
    public static void main(String[] args) {
        System.out.println("Tic-tac-toe by Eriche Gonzales, CMPU-102 special edition");

        int boardSize = 0;

        // prints board based on size inputed
        if (args.length > 0 && Integer.parseInt(args[0]) >= 1 && Integer.parseInt(args[0]) <= 9) {
            boardSize = Integer.parseInt(args[0]);
        }
        else if (args.length == 0) {
            boardSize = 3;
        }
        else {
            boardSize = 3;
            System.out.printf("Specified board size %s out of range [1,9], using default value of 3 instead. \n", args[0]);
        }

        // creates a new game
        Game game = new Game(boardSize);

        // creates new players and assigns a symbol
        players[0] = new HumanPlayer(game, 'X');
        players[1] = new CpuPlayer(game, 'O');

        // keep playing game unless quit is called
        while(true) {
            char playGame = game.playSingleGame();
            
            if (playGame == 'Q') break;
        }
        
        // returns game stats
        System.out.println(stats);
    }
}
