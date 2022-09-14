
/**
 * A computer-controlled Tic-tac-toe player that implements a random playing strategy.
 *
 * @author Eriche Gonzales
 * @date April 1, 2022
 */
public class CpuPlayer extends APlayer
{
    /**
     * Constructor for objects of class CPUTicPlayer
     */
    public CpuPlayer​(Game game, char symbol) {
        super(game, symbol);
    }

    /**
     * Picks a move.
     * It does this by generating specific moves within the game board boundaries until it finds the correct position. Assumes the game isn't over yet, otherwise it'd go into an infinite loop.
     * @return the chosen move. Because the CPU never quits, this implementation method never returns null.
     */
    @Override
    public Move pickMove() {
        // random row and column chosen within limits of the game board
        int row;
        int col;
        char rowChar;
        char colChar;

        // Moves generated based on status of board
        Move cpuMove = new Move(-1, -1);

        Move blockRow = blockRow();
        Move blockCol = blockCol();
        Move blockMainDiag = blockMainDiag();
        Move blockAntiDiag = blockAntiDiag();

        Move winRow = winRow();
        Move winCol = winCol();
        Move winMainDiag = winMainDiag();
        Move winAntiDiag = winAntiDiag();

        Move cornerMove = cornerMove();
        // Move randomCorner = randomCorner();

        while(true) {
            if (winRow != null) {
                row = winRow.row;
                col = winRow.col;
                cpuMove = winRow;
            }
            else if (winCol != null) {
                row = winCol.row;
                col = winCol.col;
                cpuMove = winCol;
            }
            if (winMainDiag != null) {
                row = winMainDiag.row;
                col = winMainDiag.col;
                cpuMove = winMainDiag;
            }
            if (winAntiDiag != null) {
                row = winAntiDiag.row;
                col = winAntiDiag.col;
                cpuMove = winAntiDiag;
            }
            else if (blockRow != null) {
                row = blockRow.row;
                col = blockRow.col;
                cpuMove = blockRow;
            }
            else if (blockCol != null) {
                row = blockCol.row;
                col = blockCol.col;
                cpuMove = blockCol;
            }
            else if (blockMainDiag != null) {
                row = blockMainDiag.row;
                col = blockMainDiag.col;
                cpuMove = blockMainDiag;
            }
            else if (blockAntiDiag != null) {
                row = blockAntiDiag.row;
                col = blockAntiDiag.col;
                cpuMove = blockAntiDiag;
            }
            else if (cornerMove != null) {
                row = cornerMove.row;
                col = cornerMove.col;
                cpuMove = cornerMove;
            }
            // else if (randomCorner != null) {
                // row = randomCorner.row;
                // col = randomCorner.col;
                // cpuMove = randomCorner;
            // }
            else {
                while (true){
                    row = (int) (game.boardSize*Math.random());
                    col = (int) (game.boardSize*Math.random());
                    if (game.board[row][col] == game.SYMBOL_BLANK) {
                        cpuMove.row = row;
                        cpuMove.col = col;
                        break;
                    }                   
                }
            }

            rowChar = (char) (row+65);
            colChar = (char) (col+49);
            System.out.println("CPU's move: " + rowChar + colChar);
            
            return cpuMove;
        }
    }

    /**
     * Picks a move to block the the human player from completing a row. It does this by placing a move in the empty index.
     * @return the chosen move.
     */
    public Move blockRow() {
        Move cpuMove = new Move(-1, -1);
        int blankCount = 0; 
        int humanCount = 0;
        int rowToBlock = 0;
        int colToBlock = 0;

        for (int row=0; row < game.boardSize; row++){
            for (int col = 0; col < game.boardSize; col++){
                if (game.board[row][col] == game.SYMBOL_BLANK){
                    blankCount++;
                    rowToBlock = row;
                    colToBlock = col;
                }
                if (game.board[row][col] == game.SYMBOL_HUMAN){
                    humanCount++;
                }
                if (blankCount > 1){
                    break;
                }
            }
            if (humanCount == (game.boardSize-1) && (game.board[rowToBlock][colToBlock] == game.SYMBOL_BLANK)) {
                cpuMove = new Move(rowToBlock, colToBlock);
                return cpuMove;
            }

            blankCount = 0; 
            humanCount = 0;
            rowToBlock = 0;
            colToBlock = 0;
        }
        return null;
    }

    /**
     * Picks a move to block the the human player from completing a column. It does this by placing a move in the empty index.
     * @return the chosen move.
     */
    public Move blockCol() {
        Move cpuMove = new Move(-1, -1);
        int blankCount = 0; 
        int humanCount = 0;
        int rowToBlock = 0;
        int colToBlock = 0;

        for (int row=0; row < game.boardSize; row++){
            for (int col = 0; col < game.boardSize; col++){
                if (game.board[col][row] == game.SYMBOL_BLANK){
                    blankCount++;
                    colToBlock = col;
                    rowToBlock = row;
                }
                if (game.board[col][row] == game.SYMBOL_HUMAN){
                    humanCount++;
                }
                if (blankCount > 1){
                    break;
                }
            }
            if (humanCount == (game.boardSize-1) && (game.board[colToBlock][rowToBlock] == game.SYMBOL_BLANK)) {
                cpuMove = new Move(colToBlock, rowToBlock);
                return cpuMove;
            }

            blankCount = 0; 
            humanCount = 0;
            rowToBlock = 0;
            colToBlock = 0;   
        }
        return null;
    }

    /**
     * Picks a move to block the the human player from completing the main diagonal. It does this by placing a move in the empty index.
     * @return the chosen move.
     */
    public Move blockMainDiag() {
        Move cpuMove = new Move(-1, -1);
        int blankCount = 0; 
        int humanCount = 0;
        int rowToBlock = 0;
        int colToBlock = 0;

        for (int row=0; row < game.boardSize; row++){
            if (game.board[row][row] == game.SYMBOL_BLANK){
                blankCount++;
                rowToBlock = row;
            }
            if (game.board[row][row] == game.SYMBOL_HUMAN){
                humanCount++;
            }
            if (blankCount > 1){
                break;
            }
        }
        if (humanCount == (game.boardSize-1) && (game.board[rowToBlock][rowToBlock] == game.SYMBOL_BLANK)) {
            cpuMove = new Move(rowToBlock, rowToBlock);
            return cpuMove;
        }

        blankCount = 0; 
        humanCount = 0;
        rowToBlock = 0;
        colToBlock = 0;

        return null;
    }

    /**
     * Picks a move to block the the human player from completing the antidiagonal. It does this by placing a move in the empty index.
     * @return the chosen move.
     */
    public Move blockAntiDiag() {
        Move cpuMove = new Move(-1, -1);
        int blankCount = 0; 
        int humanCount = 0;
        int rowToBlock = 0;
        int colToBlock = 0;

        for (int row=game.boardSize-1; row >= 0; row--){
            if (game.board[game.boardSize-row-1][row] == game.SYMBOL_BLANK){
                blankCount++;
                rowToBlock = game.boardSize-row-1;
                colToBlock = row;
            }
            if (game.board[game.boardSize-row-1][row] == game.SYMBOL_HUMAN){
                humanCount++;
            }
            if (blankCount > 1){
                break;
            }

        }
        if (humanCount == (game.boardSize-1) && (game.board[rowToBlock][colToBlock] == game.SYMBOL_BLANK)) {
            cpuMove = new Move(rowToBlock, colToBlock);
            return cpuMove;
        }

        blankCount = 0; 
        humanCount = 0;
        rowToBlock = 0;
        colToBlock = 0;

        return null;
    }


    /**
     * Picks a move to complete a row. It does this by placing a move in the empty index.
     * @return the chosen move.
     */
    public Move winRow() {
        Move cpuMove = new Move(-1, -1);
        int blankCount = 0; 
        int cpuCount = 0;
        int rowToWin = 0;
        int colToWin = 0;

        for (int row=0; row < game.boardSize; row++){
            for (int col = 0; col < game.boardSize; col++){
                if (game.board[row][col] == game.SYMBOL_BLANK){
                    blankCount++;
                    rowToWin = row;
                    colToWin = col;
                }
                if (game.board[row][col] == game.SYMBOL_CPU){
                    cpuCount++;
                }
                if (blankCount > 1){
                    break;
                }
            }
            if (cpuCount == (game.boardSize-1) && (game.board[rowToWin][colToWin] == game.SYMBOL_BLANK)) {
                cpuMove = new Move(rowToWin, colToWin);
                return cpuMove;
            }

            blankCount = 0; 
            cpuCount = 0;
            rowToWin = 0;
            colToWin = 0;
        }
        return null;
    }

    /**
     * Picks a move to complete a column. It does this by placing a move in the empty index.
     * @return the chosen move.
     */
    public Move winCol() {
        Move cpuMove = new Move(-1, -1);
        int blankCount = 0; 
        int cpuCount = 0;
        int rowToWin = 0;
        int colToWin = 0;

        for (int row=0; row < game.boardSize; row++){
            for (int col = 0; col < game.boardSize; col++){
                if (game.board[col][row] == game.SYMBOL_BLANK){
                    blankCount++;
                    colToWin = col;
                    rowToWin = row;
                }
                if (game.board[col][row] == game.SYMBOL_CPU){
                    cpuCount++;
                }
                if (blankCount > 1){
                    break;
                }
            }
            if (cpuCount == (game.boardSize-1) && (game.board[colToWin][rowToWin] == game.SYMBOL_BLANK)) {
                cpuMove = new Move(colToWin, rowToWin);
                return cpuMove;
            }

            blankCount = 0; 
            cpuCount = 0;
            rowToWin = 0;
            colToWin = 0;   
        }
        return null;
    }

    /**
     * Picks a move to complete the main diagonal. It does this by placing a move in the empty index.
     * @return the chosen move.
     */
    public Move winMainDiag() {
        Move cpuMove = new Move(-1, -1);
        int blankCount = 0; 
        int cpuCount = 0;
        int rowToWin = 0;

        for (int row=0; row < game.boardSize; row++){
            if (game.board[row][row] == game.SYMBOL_BLANK){
                blankCount++;
                rowToWin = row;
            }
            if (game.board[row][row] == game.SYMBOL_CPU){
                cpuCount++;
            }
            if (blankCount > 1){
                break;
            }
        }
        if (cpuCount == (game.boardSize-1) && (game.board[rowToWin][rowToWin] == game.SYMBOL_BLANK)) {
            cpuMove = new Move(rowToWin, rowToWin);
            return cpuMove;
        }

        blankCount = 0; 
        cpuCount = 0;
        rowToWin = 0;

        return null;
    }

    /**
     * Picks a move to complete the antidiagonal. It does this by placing a move in the empty index.
     * @return the chosen move.
     */
    public Move winAntiDiag() {
        Move cpuMove = new Move(-1, -1);
        int blankCount = 0; 
        int cpuCount = 0;
        int rowToWin = 0;
        int colToWin = 0;

        for (int row=game.boardSize-1; row >= 0; row--){
            if (game.board[game.boardSize-row-1][row] == game.SYMBOL_BLANK){
                blankCount++;
                rowToWin = game.boardSize-row-1;
                colToWin = row;
            }
            if (game.board[game.boardSize-row-1][row] == game.SYMBOL_CPU){
                cpuCount++;
            }
            if (blankCount > 1){
                break;
            }

        }
        if (cpuCount == (game.boardSize-1) && (game.board[rowToWin][colToWin] == game.SYMBOL_BLANK)) {
            cpuMove = new Move(rowToWin, colToWin);
            return cpuMove;
        }

        blankCount = 0; 
        cpuCount = 0;
        rowToWin = 0;
        colToWin = 0;

        return null;
    }

    /**
     * Picks a move in the corner of the board. It does this by placing a move in the empty index.
     * @return the chosen move.
     */
    public Move cornerMove() {
        Move cpuMove = new Move(-1, -1);
        int endIndex = game.boardSize-1;

        if (game.board[0][0] == game.SYMBOL_BLANK) {
            cpuMove = new Move(0,0);
            return cpuMove;
        }

        if (game.board[0][endIndex] == game.SYMBOL_BLANK) {
            cpuMove = new Move(0, endIndex);
            return cpuMove;
        }

        if (game.board[endIndex][0] == game.SYMBOL_BLANK) {
            cpuMove = new Move(endIndex, 0);
            return cpuMove;
        }

        if (game.board[endIndex][endIndex] == game.SYMBOL_BLANK) {
            cpuMove = new Move(endIndex, endIndex);
            return cpuMove;
        }

        return null;
    }
    
    // Method does not work after testing, will not implement in game
    public Move randomCorner() {
        double random = Math.random();
        Move cpuMove = new Move(-1, -1);
        
        if (random >=0 && random <= 0.24) {
            cpuMove = cornerMove();
            return cpuMove;
        }
        else if (random >=0.25 && random <= 0.49) {
            cpuMove = cornerMove();
            return cpuMove;
        }
        else if (random >=0.50 && random <= 0.74) {
            cpuMove = cornerMove();
            return cpuMove;
        }
        else if (random >=0.74 && random <= 1) {
            cpuMove = cornerMove();
            return cpuMove;
        }
        
        return null;
    }
}
