
/**
 * An abstract class representing a generic Tic-tac-toe game player.
 *
 * @version CMPU-102 special edition
 * @author Eriche Gonzales
 * @date April 1, 2022
 */
public class APlayer
{
    /**
     * the game the player is playing
     */
    protected Game game;
    
    /**
     * character to represent the player's moves on the board
     */
    protected char symbol;
    
    /**
     * Empty constructor for objects of class ATicPlayer.
     */
    protected APlayer() {
        
    }
    
    /**
     * Constructor for objects of class ATicPlayer.
     * @param game the tic-tac-toe game that is to be played
     * @param symbol the character symbol to be used to represent this player's moves
     */
    protected APlayer(Game game, char symbol) {
        this.game = game;
        this.symbol = symbol;
    }
    
    /**
     * Returns the symbol that represents this player.
     * @return a char representing the player's symbol
     */
    public char getSymbol() {
        return symbol;
    }
    
    /**
     * Makes the player pick his next move. Each concrete type of player should implement this method according to its own semantics. The only thing that is required is that the method returns a valid move (i.e. an unocuppied position within the bounds of the game board) or null to signify that the player desires to exit the program.
     * @return the move picked by the player
     */
    public Move pickMove() {
        return null;
    }
}
