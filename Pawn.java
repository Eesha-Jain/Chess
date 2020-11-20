import java.util.Scanner;
import java.util.HashMap;

public class Pawn extends Piece {

    private final char NAME = 'P';
    private boolean isMoved;
    private int side;

    public Pawn(int side) {
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
        
        if (side == 0) {
            System.out.println(notation);
            System.out.println(notation.charAt(1) - notation.charAt(3));
            if (notation.charAt(1) - notation.charAt(3) == 1 || (!isMoved && notation.charAt(1) - notation.charAt(3) == 2)) {
                isMoved = true;
                return true;
            }
        } else {
            if (notation.charAt(3) - notation.charAt(1) == 1  || (!isMoved && notation.charAt(3) - notation.charAt(1) == 2)) {
                isMoved = true;
                return true;
            }
        }

        return false;
    }
}