package pl.sda.testing.bookstore;

import java.util.List;

public interface BookApi {

    List<Book> fetchBooksByAuthor(String author);
}
