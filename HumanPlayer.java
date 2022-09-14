import java.util.Scanner;
/**
 * A human Tic-tac-toe player that reads moves from the keyboard.
 *
 * @version CMPU-102 special edition
 * @author Eriche Gonzales
 * @date April 1, 2022
 */
public class HumanPlayer extends APlayer
{
    /**
     * Constructor for objects of class HumanTicPlayer
     * @param game the tic-tac-toe game that is to be played
     * @param the character symbol to be used to represent this player's moves
     */
    public HumanPlayer(Game game, char symbol) {
        super(game, symbol);
    }

    /**
     * This method asks the user to pick a tic-tac-toe move. Moves are read from the keyboard and are specified by two characters rc, where r is a letter representing the row and c is a digit representing the column. For instance a1 means the 1st column of the first row and c2 means the 2nd column of the 3rd row. If the user specifies: a position that is outside the bound of the game board or, a position that is already occupied, an appropriate error message is shown and the user is asked for another position. If the user writes quit (regardless of case), the method returns null, signifying that the program should terminate.
     * @return the move the user chose or null if the user wants to exit
     */
    @Override
    public Move pickMove() {
        Scanner scanner = new Scanner(System.in);
        String userInput = "";
        Move humanMove = new Move(-1, -1);
        boolean validInput = false;

        while(!validInput) {
            System.out.println("Your move (quit to exit):");
            userInput = scanner.nextLine();
            
            if (userInput.equals("quit")) {
                break;
            }
            else if (userInput.length() != 2) {
                System.out.println("Please specify two characters, one for the row and another for the column. E.g. A1.");
            }
            else {
                userInput = userInput.toUpperCase();
                
                // stores the user input as separate characters
                char rowInput = userInput.charAt(0);
                char colInput = userInput.charAt(1);

                // typecasts the characters into integers
                int humanRow = (int) (rowInput-65);
                int humanCol = (int) (colInput-49);
                humanMove.row = humanRow;
                humanMove.col = humanCol;

                validInput = (game.isValidMove(humanMove) == 'V');
            }
        }
        
        return humanMove;
    }
}