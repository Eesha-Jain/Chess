import java.util.Scanner;
import java.util.HashMap;

public class Bishop extends Piece {

    private final char NAME = 'B';
    private int side;
    
    public Bishop(int side) {
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

        if (Math.abs(notation.charAt(0) - notation.charAt(2)) == Math.abs(notation.charAt(1) - notation.charAt(3)))
            return true;

        return false;
    }
}