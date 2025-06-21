import java.util.List;

public class OrderService {
    Order Order;
    public Order placeOrder(User user, List<Long>BookId)
    {
        double totalAmount = 0;
        for (Long bookId : BookId) {
            Book book = book.getBookId(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book not found"));
            
            if (book.getStock() <= 0) throw new RuntimeException("Book out of stock");
            
            total += book.getPrice();
            book.setStock(book.getStock() - 1);
            bookRepository.save(book);
        }
    }
}
