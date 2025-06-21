package ClearFood;
// User Registration :
// register_user(“Pralove”, “M”, “phoneNumber-1”, “HSR”)
// register_user(“Nitesh”, “M”, “phoneNumber-2”, “BTM”)
// register_user(“Vatsal”, “M”, “phoneNumber-3”, “BTM”)
// login_user(“phoneNumber-1”)

// Restaurant Registration :
// register_restaurant(“Food Court-1”, “BTM/HSR”, “NI Thali”, 100, 5)
// NOTE: we will have 2 delimiters in input : ',' to specify separate fields & '/' to identify different pincodes.
// register_restaurant(“Food Court-2”, “BTM/pincode-2”, “Burger”, 120, 3)
// login_user(“phoneNumber-2”)
// register_restaurant(“Food Court-3”, “HSR”, “SI Thali”, 150, 1)
// login_user(“phoneNumber-3”)

// Fetch Restaurant List :
// show_restaurant(“Price”) —-> Output : Food Court-2, Burger | Food Court-1, NI Thali

// Place Order :
// place_order(“Food Court-1”, 2) —-> Output: Order Placed Successfully.
// place_order(““Food Court-2”, 7) —-> Output : Cannot place order

// Add Review :
// create_review(“Food Court-2”, 3,"Good FOod ")
// create_review(“Food Court-1”, 5)

// show_restaurant(“rating”) —->
// Output : Food Court-1, NI Thali Food Court-2, Burger

// login_user(“phoneNumber-1”) —> update_quantity(“Food Court-2”, 5)
// Output: Food Court-2, BTM, Burger - 8

// update_location(“Food Court-2”, “BTM/HSR”) —>
// Output: Food Court-2, “BTM/HSR”, Burger - 8

import java.util.HashSet;

import Twitter.User;

import java.util.*;
class User{
    String name;
    String phoneNo;
    String gender;
    String pincode;
    public User(String name,String gender, String phoneNo, String pinCode)
    {
        this.name = name;
        this.gender = gender;
        this.phoneNo = phoneNo;
        this.pincode = pincode;
    }
}
// register_restaurant(“Food Court-1”, “BTM/HSR”, “NI Thali”, 100, 5)
class Restaurant{
    String name;
    String pincode;
    String food;
    Integer quaIntity;
    Double price;
    public Set<String>pinCodes;
    List<Review>reviews;
    private void assignPincodes(String pincode)
    {
        String[]temp = pincode.split("/");
        for(String i:temp)
        {
            pinCodes.add(i);
        }
    }
    public double getRating() {
        if (reviews == null || reviews.isEmpty()) {
            return 0;
        }
        double sum = 0;
        for (Review review : reviews) {
            sum += review.rating;  // assuming Review has a getRating() method
        }
        return sum / reviews.size();
    }

    public Restaurant(String name, String pincode, String food, Integer quantity, Double price)
    {
        this.name = name;
        this.pincode = pincode;
        this.food = food;
        this.quaIntity = quantity;
        this.price = price;
        pinCodes = new HashSet<>();
        assignPincodes(pincode);
    }
}
class Review{
    Integer rating;
    String review;
}
class FoodService{
    Map<String, User>userMap;
    Map<String,Restaurant>resMap;
    User logUser;
    public void register_user(String name,String gender, String phoneNo, String pinCode)
    {
        User u = new User(name,gender, phoneNo,pinCode);
        userMap.put(phoneNo, u);
    }
    public void login_user(String phoneNo)
    {
        logUser =  userMap.get(phoneNo);
    }
    public void register_restaurant(String name, String pincode, String food, Integer quantity, Double price)
    {
        Restaurant r = new Restaurant(name, pincode, food, quantity, price);
        resMap.put(name,r);
    }
    public void create_review(String res_name,Integer revie, String reviewString)
    {
        Review r = new Review();
        Restaurant r = resMap.get(res_name);
        r.setRAtng
    }
    public List<Restaurant>showRestaurants(String sortType)
    {
        List<Restaurant>res = new ArrayList<>();

        for (Map.Entry<String, Restaurant> entry : resMap.entrySet()) {
            if(entry.getValue().pinCodes.contains(entry.getKey()))
            {
                res.add(entry.getValue());
            }
        }
        switch (sortType) {
            case "Price":
            Collections.sort(res, (r1, r2) -> Double.compare(r1.getRating(), r2.getRating()));
                break;
            case "Rating":
            Collections.sort(res, (r1, r2) -> Double.compare(r1.price, r2.price));
            break;
        }
        return res;
    }
}

public class ClearFood {
    
}
