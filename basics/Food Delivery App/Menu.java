import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Menu {
    HashMap<String, Double> menu = new HashMap<>();
    public Menu(){
      addMenu();
    }
    public void addMenu(){
        menu.put("Pizza", 8.99);
        menu.put("Burger", 5.99);
    }
}
