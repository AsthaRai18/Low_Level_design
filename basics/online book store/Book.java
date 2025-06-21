public class Book {
   
        private int BookId;
        private String title;
        private String author;
        private String price;
        private int rating;
        private int Stock;
        void Book(int BookId, String title, String author, String price, int rating, int Stock) {
            this.BookId = BookId;
            this.title = title;
            this.author = author;
            this.price = price;
            this.rating = rating;
            this.Stock = Stock;
        }
        public Book getBookId(Long id) {
            return Book;
    }
        public Book orElseThrow(Object object) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'orElseThrow'");
        }

}
