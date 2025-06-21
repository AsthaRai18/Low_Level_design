public abstract class User {
    protected Long id;
    protected String name;
    protected String phoneNumber;
    public User(Long id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
}
