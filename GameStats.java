
/**
 * A container of game statistics.
 *
 * @version CMPU-102 special edition
 * @author Eriche Gonzales
 * @date April 1, 2022
 */
public class GameStats
{
    /**
     * represents the number of games lost by the human player, so far
     */
    static int nlosses;

    /**
     * represents the number of games that ended in a tie, so far
     */
    static int nties;

    /**
     * represents the number of games won by the human player, so far
     */
    static int nwins;

    /**
     * represents the number of games played, so far
     */
    double ngames = 0;

    /**
     * Constructor for objects of class GameStats. All fields are initialized to zero.
     */
    public GameStats() {
        this.nlosses = nlosses;
        this.nties = nties;
        this.nwins = nwins;

        nlosses = 0;
        nties = 0;
        nwins = 0;
    }

    /**
     * Increments the number of human wins.
     */
    public void recordWin() {
        nwins++;
        ngames++;
    }

    /**
     * Increments the number of ties.
     */
    public void recordTie() {
        nties++;
        ngames++;
    }

    /**
     * Increments the number of human losses.
     */
    public void recordLoss() {
        nlosses++;
        ngames++;
    }

    /**
     * Returns a textual representation of the statistics contained in this object.
     * @override toString in class Object
     * @return a textual representation of the statistics contained in this object
     */
    public String toString() {
        ngames = nwins + nties + nlosses;

        if (ngames == 0) {
            System.out.println("0 games: 0 wins (0%), 0 ties (0%), 0 losses (0%)");
        }
        else {
            int pwins = (int) ((nwins/ngames)*100);
            int pties = (int) ((nties/ngames)*100);
            int plosses = (int) ((nlosses/ngames)*100);

            System.out.println( (int) (ngames) + " games: " + nwins + " wins (" + pwins + "%), " + nties +" ties (" + pties + "%), " + nlosses + " losses (" + plosses + "%)");
        }

        return "Goodbye!";
    }
}
