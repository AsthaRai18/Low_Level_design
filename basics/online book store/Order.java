import java.util.List;

public class Order {
    private int orderId;
    private int userId;
    private List<Integer> BookList;
    private String status;
    private String PaymentStatus;
    void Order(int orderId, int userId, List<Integer> BookList, String status, String PaymentStatus) {
        this.orderId = orderId;
        this.userId = userId;
        this.BookList = BookList;
        this.status = status;
        this.PaymentStatus = PaymentStatus;
    }
   
    void cancelOrder()
    {

    }
}
