package ChessPieces;

import java.util.Scanner;
import java.util.HashMap;

public class Knight extends Piece {

    private final char NAME = 'N';
    private int side;

    public Knight(int side) {
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

        if (            
            (Math.abs(notation.charAt(0) - notation.charAt(2))== 2 && Math.abs(notation.charAt(1) - notation.charAt(3)) == 1) 
            
            || 
            
            (Math.abs(notation.charAt(0) - notation.charAt(2)) == 1 && Math.abs(notation.charAt(1) - notation.charAt(3)) == 2))
            
            return true;

        return false;
    }
}