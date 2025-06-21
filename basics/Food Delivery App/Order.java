import java.util.*;
public class Order {
    long orderId;
    long userId;
    long restaurantId;
    List<String> items = new ArrayList<>();
    public Order()
    {
        addItems();
    }
    public void addItems()
    {
        items.add("Pizza");
        items.add("Burger");
    }
}
