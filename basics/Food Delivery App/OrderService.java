public class OrderService{
    public void placeOrder(User user, Restaurant restaurant, Order order){
        System.out.println("Order placed successfully!");
    }
    public int orderTotal(User user, Restaurant restaurant, Order order){
        int total = 0;
        for(String item: order.items){
            Menu menuInstance = new Menu();
            total += menuInstance.menu.get(item);
        }
        return total;
    }
    public void cancelOrder(User user, Restaurant restaurant, Order order){
        System.out.println("Order cancelled successfully!");
    }
    public void updateOrder(User user, Restaurant restaurant, Order order){
        System.out.println("Order updated successfully!");
    }
    public void viewOrder(User user, Restaurant restaurant, Order order){
        System.out.println("Order viewed successfully!");
    }
}