package snakeLadder;

public class player {
    int playerId;
    String playerName;
    int row; 
    int col;
    public player(int id, String plString, int row, int col)
    {
        this.playerId = id;
        this.playerName = plString;
        this.row = row;
        this.col = col;
    }
    public Integer getRow()
    {
        return row;
    }
    public Integer getCol()
    {
        return col;
    }
}
