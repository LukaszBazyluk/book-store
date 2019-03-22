package pl.sda.testing.bookstore;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class BookServiceTest {
    @Mock
    private List<Book> list;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        bookService = new BookService(bookApi, bookStore);
    }

    @Mock
    private BookApi bookApi;

    private BookService bookService;

    @Mock
    private BookStore bookStore;

    @Test(expected = ApiException.class)
    public void testBookAIOException() {
        //given
        when(bookApi.fetchBooksByAuthor(anyString())).thenThrow(new ApiException());

        //when
        List<Book> actual = bookService.getBooksFromAuthor("Test");


    }

    @Test
    public void getBooksFromAuthorTestAndCerifySavingToBookStore() {


        //given
        when(bookApi.fetchBooksByAuthor(anyString())).thenReturn(createBookList());
        List<Book> expected = createBookList();

        //when
        List<Book> actual = bookService.getBooksFromAuthor("Test");

        //then
        Assert.assertEquals(expected, actual);
        Mockito.verify(bookStore).saveBooks(eq(expected));

    }

    private List<Book> createBookList() {
        return Arrays.asList(
                new Book("Java", "Rafal Paliwoda", 2019),
                new Book("Java czy sen", "Marcin Waski", 2029),
                new Book("Jade na Javie", "Krzysiek Poncki", 2059)
        );
    }


}