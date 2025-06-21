public class Seat
{
    Integer seaNumber;
    Integer col;
    Integer row;
    Boolean isReserved;
    SeatType seatType;
    public Seat(Integer seaNumber, Integer col, Integer row, Boolean isReserved, SeatType seatType) {
        this.seaNumber = seaNumber;
        this.col = col;
        this.row = row;
        this.isReserved = isReserved;
        this.seatType = seatType;
    }
    public Integer getSeaNumber() {
        return seaNumber;
    }
    public void setSeaNumber(Integer seaNumber) {
        this.seaNumber = seaNumber;
    }
    public Integer getCol() {
        return col;
    }
    public void setCol(Integer col) {
        this.col = col;
    }
    public Integer getRow() {
        return row;
    }
    public void setRow(Integer row) {
        this.row = row;
    }
    public Boolean getIsReserved() {
        return isReserved;
    }
}