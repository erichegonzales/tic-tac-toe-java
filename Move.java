
/**
 * Represents a player move in a Tic-tac-game.
 *
 * @version CMPU-102 special edition
 * @author Eriche Gonzales
 * @date April 1, 2022
 */
public class Move
{
    /**
     * the move's column
     */
    int col;
    
    /**
     * the move's row
     */
    int row;
    
    /**
     * Constructor for objects of class TicMove.
     * @param row the move's row
     * @param col the move's col
     */
    public Move(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
