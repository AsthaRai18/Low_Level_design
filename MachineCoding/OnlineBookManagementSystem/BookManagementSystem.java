package OnlineBookManagementSystem;
import java.util.*;
enum BookStatus
{
    FREE, ISSUED
}
class Book 
{
    private UUID id;
    private String title;
    private String author;
    private String genre;
    public Book(UUID id, String title, String author, String genre)
    {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
    }
}
class User
{
    long userId;
    String userName;
    Boolean isRegistered;
}
class BookManagement{
    List<Book>books;
    Map<String,List<Book>>titleMap;
    Map<String,List<Book>>authMap;
    Map<String,List<Book>>genMap;
    Map<Integer,Book>bookMap;
    //if we are using multiple features then in that case ??
    public BookManagement()
    {
        titleMap = new HashMap<>();
        authMap = new HashMap<>();
        genMap = new HashMap<>();
    }
    public void addBook(String title, String auString, String genre )
    {
        UUID randomUUID = UUID.randomUUID();
        Book b1 = new Book(randomUUID, title, auString,genre);
        books.add(b1);
        if(!titleMap.containsKey(title))
            titleMap.put(title,new ArrayList<>());
        titleMap.get(title).add(b1);
        if(!authMap.containsKey(title))
            authMap.put(auString,new ArrayList<>());
        authMap.get(auString).add(b1);
        if(!genMap.containsKey(title))
            genMap.put(auString,new ArrayList<>());
        // similarly for others too 
    }
    public Book getBookFromBookId(UUID id)
    {
        return bookMap.get(id);
    }
    public List<String>allAuthors()
    {
        return new ArrayList<>(authMap.keySet());
    }
    public List<Book>getBooksFromAuthors(String author)
    {
        return authMap.get(author);
    }
}
    public class BookManagementSystem {
    
}
