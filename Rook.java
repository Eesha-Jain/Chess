
public class Rook extends Piece {

    private final char NAME = 'R';
    private int side;
    private boolean isMoved;

    public Rook(int side) {
        this.side = side;
        this.isMoved = false;
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
        
        if (Math.abs(notation.charAt(0) - notation.charAt(2)) == 0 || Math.abs(notation.charAt(1) - notation.charAt(3)) == 0 )
            return true;

        return false;
    }
}