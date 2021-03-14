package ChessPieces;

import java.util.*;

public class Rook extends Piece {

    private final char NAME = 'R';
    private int side;
    private boolean isMoved;
    private HashMap<Character, Character> arr;

    public Rook(int side) {
        this.side = side;
        this.isMoved = false;

        arr = new HashMap<>() {{
            put('a', '0');
            put('b', '1');
            put('c', '2');
            put('d', '3');
            put('e', '4');
            put('f', '5');
            put('g', '6');
            put('h', '7');
        }};
    }

    public char getName() {
        return NAME;
    }

    public int getSide() {
        return side;
    }

    public boolean isMoved() {
        return isMoved;
    }
    
    public boolean valid(int side, String notation, Piece [] [] board) {
        if (this.side != side)
            return false;
        
        int yaf = Integer.parseInt(arr.get(notation.charAt(2)) + "");
        int xaf = Integer.parseInt(notation.charAt(3) + "") - 1;
        int yorg = Integer.parseInt(arr.get(notation.charAt(0)) + "");
        int xorg = Integer.parseInt(notation.charAt(1) + "") - 1;

        if (Math.abs(notation.charAt(0) - notation.charAt(2)) == 0) {
            int minn = Math.min(xorg, xaf);
            int maxx = Math.max(xorg, xaf);
            
            for (int i = minn + 1; i < maxx; i++) {
                if (board[i][yorg].getName() != '-')
                    return false;
            }
            return true;
        }

        if (Math.abs(notation.charAt(1) - notation.charAt(3)) == 0) {
            int minn = Math.min(yorg, yaf);
            int maxx = Math.max(yorg, yaf);

            for (int i = minn + 1; i < maxx; i++) {
                if (board[xorg][i].getName() != '-')
                    return false;
            }
            return true;
        }
  
        return false;
    }
}