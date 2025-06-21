public class User
{
    private String name;
    private String email;
    private Role role;
    public User(String name, String email, Role role)
    {
        this.name = name;
        this.email = email;
        this.role = role;
    }
    public Role getRole()
    {
        return role;
    }
}