package Zepto;
import java.util.*;
enum UserType
{
    REGULAR, PREMIUM
}
enum ProductType
{
    GROCERY, COSMETIC,ELECTRONIC
}
class Item
{
    private int ProductId;
    private String productName;
    private Double price;
}
class Cart{
    private int cartid;
    private List<Item>cartItem;
    private Double price;

}
class User
{
    private int userId;
    private String userName;
    private UserType userType;
}
class Rule{

}
class Coupon{
    private int couponId;
    
}
public class ZeptoCouponService {
    public static void main(String args[])
    {

    }
}
