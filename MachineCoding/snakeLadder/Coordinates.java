package snakeLadder;

public class Coordinates {
    private Integer startRow;
    private Integer startCol;
    private Integer endRow;
    private Integer endCol;
    public Coordinates(Integer row, Integer col, Integer endInteger, Integer endInteger2)
    {
        this.startRow = row;
        this.startCol = col;
        this.endRow = endInteger;
        this.endCol = endInteger2;
    }
    public Integer getRow1()
    {
        return startRow;
    }
    public Integer getCol1()
    {
        return startCol;
    }
    public Integer getRow2()
    {   
        return endRow;
    }
    public Integer getCol2()
    {
        return endCol;
    }
}
