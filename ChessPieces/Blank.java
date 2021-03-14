package ChessPieces;

public class Blank extends Piece {
    
    private char name;
    private int side;

    public Blank() {
        this.name = '-';
        this.side = 2;
    }

    public char getName() {
        return name;
    }

    public int getSide() {
        return side;
    }

    public boolean valid(int side, String notation, Piece [] [] board) {
        return false;
    }
}