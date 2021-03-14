package ChessPieces;

public abstract class Piece {

    public char getName() {return '-';}
    public int getSide() {return 2;}
    
    public boolean valid(int side, String notation, Piece [] []board) {
        return false;
    }

    public boolean isMoved() {
        return true;
    }
}