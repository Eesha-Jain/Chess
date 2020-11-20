
import java.util.Scanner;
import java.util.HashMap;

public class King extends Piece {

    private final char NAME = 'K';
    private boolean isMoved;
    private int side;

    public King(int side) {
        isMoved = false;
        this.side = side;
    }

    public char getName() {
        return NAME;
    }

    public int getSide() {
        return side;
    }
    
    public boolean valid(int side, String notation, Piece [] [] board) {
        if (this.side != side)
            return false;

        if (!isMoved) {
            if (notation.charAt(0) == '4' && notation.charAt(2) == '6') {
                //white
                if (side == 0) {
                    if (board[7][5].getName() == '-' && board[7][6].getName() == '-' && board[7][7].getName() == 'R')
                        if (board[7][7].isMoved() == false)
                            return true;
                } else { //red
                    if (board[0][5].getName() == '-' && board[0][6].getName() == '-' && board[0][7].getName() == 'R')
                        if (board[7][7].isMoved() == false)
                            return true;
                }
            } else {
                //white
                if (side == 0) {
                    if (board[7][1].getName() == '-' && board[7][2].getName() == '-' && board[7][3].getName() == '-' && board[7][0].getName() == 'R')
                        if (board[7][7].isMoved() == false)
                            return true;
                } else { //red
                    if (board[0][1].getName() == '-' && board[0][2].getName() == '-' && board[0][3].getName() == '-' && board[0][0].getName() == 'R')
                        if (board[7][7].isMoved() == false)
                            return true;
                }
            }

            return false;
        }

        if (Math.abs(notation.charAt(0) - notation.charAt(2)) == 1|| Math.abs(notation.charAt(1) - notation.charAt(3)) == 1) {
            return true;
        }

        return false;

    }
}