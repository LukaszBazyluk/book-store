package pl.sda.testing.bookstore;

import java.util.List;

public class BookService {

    private final BookApi bookApi;
    private final BookStore bookStore;

    public BookService(BookApi bookApi, BookStore bookStore) {
        this.bookApi = bookApi;
        this.bookStore = bookStore;
    }

    public List<Book> getBooksFromAuthor(String author) {
        List<Book> books = bookApi.fetchBooksByAuthor(author);
        bookStore.saveBooks(books);
        return books;
    }
}
