public class User {
    private int userId;
private String name;
private String email;
private String password;
private String address;
void User(int userId, String name, String email, String password, String address) {
    this.userId = userId;
    this.name = name;
    this.email = email;
    this.password = password;
    this.address = address;
}
public int getUserId() {
    return userId;
}
void browseBooks() {
    // browse books
}
void addToCart(Book book) {
    // add book to cart
}
}