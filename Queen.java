
public class Queen extends Piece {

    private final char NAME = 'Q';
    private int side;

    public Queen(int side) {
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

        if (Math.abs(notation.charAt(0) - notation.charAt(2)) == 0 || Math.abs(notation.charAt(1) - notation.charAt(3)) == 0 )
            return true;

        return false;
    }
}