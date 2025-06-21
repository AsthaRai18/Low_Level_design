// Design a system for managing a restaurant. The system should handle the following
// functionalities:
// ● Allow the restaurant admin to define the number of tables.
// ● Allow restaurant admin to add, update, or remove items from the menu. Each menu item
// should have attributes such as name, price, veg / non-veg and category (starter, main
// course, dessert).
// ● Allow customers to browse the menu and allow filtering on factors (veg / non-veg,
// category), add items to their order, specify quantities, and place the order.
// ● Allow restaurant admin to manage tables in the restaurant as in number of tables.
// ● Calculate the total bill for each order, including taxes and any additional charges.
// Provide options for customers to pay the bill via various payment options, such as cash,
// credit/debit card, or online payment (card payment levy additional charges).
// Bonus Functionality:
// ● Provide a system for kitchen staff to view incoming orders, mark them as prepared, and
// notify wait-staff when orders are ready for serving.
// ● Allow multiple orders for the same table (AddItems even after 1 order was placed)
// Enums for item classification and payment
enum FoodType {
    VEG, NONVEG
}

enum FoodCategory {
    STARTER, MAIN_COURSE, DESSERT
}

enum PaymentType {
    CARD, CASH, ONLINE_PAYMENT
}

// Item class representing menu items
class Item {
    int id;
    String name;
    FoodCategory category;
    FoodType type;
    double price;

    public Item(int id, String name, FoodCategory category, FoodType type, double price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.type = type;
        this.price = price;
    }
}

// Order class with items and their quantities
class Order {
    Map<Item, Integer> items;
    boolean isPrepared;

    public Order() {
        items = new HashMap<>();
        isPrepared = false;
    }

    public void addItem(Item item, int quantity) {
        items.put(item, items.getOrDefault(item, 0) + quantity);
    }

    public double calculateTotalBill(boolean isCardPayment) {
        double total = 0;
        for (Map.Entry<Item, Integer> entry : items.entrySet()) {
            total += entry.getKey().price * entry.getValue();
        }
        total *= 1.18; // tax
        if (isCardPayment) total += total * 0.02; // card levy
        return total;
    }
}

// Customer class
class Customer {
    String id;
    List<Order> orders;

    public Customer(String id) {
        this.id = id;
        this.orders = new ArrayList<>();
    }

    public void placeOrder(Order order) {
        orders.add(order);
    }
}

// Table class
class Table {
    int tableId;
    Customer customer;
    List<Order> orders;

    public Table(int tableId) {
        this.tableId = tableId;
        this.orders = new ArrayList<>();
    }
}

// RestaurantService class
class RestaurantService {
    Map<Integer, Item> menu;
    List<Table> tables;
    int tableCount;

    public RestaurantService(int numberOfTables) {
        this.menu = new HashMap<>();
        this.tables = new ArrayList<>();
        this.tableCount = numberOfTables;
        for (int i = 1; i <= numberOfTables; i++) {
            tables.add(new Table(i));
        }
    }

    // Menu management
    public void addMenuItem(Item item) {
        menu.put(item.id, item);
    }

    public void updateMenuItem(Item item) {
        menu.put(item.id, item);
    }

    public void removeMenuItem(int itemId) {
        menu.remove(itemId);
    }

    public List<Item> browseMenu(FoodType type, FoodCategory category) {
        return menu.values().stream()
                .filter(item -> item.type == type && item.category == category)
                .toList();
    }

    public Table getTable(int tableId) {
        return tables.get(tableId - 1);
    }

    public void placeOrder(int tableId, Order order) {
        Table table = getTable(tableId);
        table.orders.add(order);
    }

    public double checkout(int tableId, PaymentType paymentType) {
        Table table = getTable(tableId);
        boolean isCard = paymentType == PaymentType.CARD;
        double total = 0;
        for (Order o : table.orders) {
            total += o.calculateTotalBill(isCard);
        }
        table.orders.clear(); // after payment
        return total;
    }
}
