package edu.hm.shareit.test;

import edu.hm.shareit.media.Book;
import edu.hm.shareit.media.Medium;
import edu.hm.shareit.mediaService.MediaService;
import edu.hm.shareit.mediaService.MediaServiceImplementation;
import edu.hm.shareit.mediaService.MediaServiceResult;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by jupiter on 4/28/17.
 */
public class MediaServiceImplementationTest {
    static final Book book = new Book("title","author","978-3551551672");

    @Test
    public void addNewBook() throws Exception {
        MediaService sut = new MediaServiceImplementation();
        MediaServiceResult wanted = MediaServiceResult.OK;
        MediaServiceResult have = sut.addBook(book);
        assertEquals(wanted,have);
    }

    @Test
    public void addExistingBook() throws Exception {
        MediaService sut = new MediaServiceImplementation();
        MediaServiceResult wanted = MediaServiceResult.ALREADY_EXISTS;
        sut.addBook(book);
        MediaServiceResult have = sut.addBook(book);
        assertEquals(wanted,have);
    }

    @Test
    public void addBookInvalidISBN() throws Exception {
        MediaService sut = new MediaServiceImplementation();
        MediaServiceResult wanted = MediaServiceResult.ILLEGAL_ISBN;
        MediaServiceResult have = sut.addBook(new Book("title","author","345345003"));
        assertEquals(wanted,have);
    }

    @Test
    public void getBook() throws Exception {
        MediaService sut = new MediaServiceImplementation();
        Book wanted = book;
        sut.addBook(book);
        Book have = (Book) sut.getBook(book.getIsbn());
        assertEquals(wanted,have);
    }

    @Test
    public void getBookNotFound() throws Exception {
        MediaService sut = new MediaServiceImplementation();
        Book wanted = null;
        Book have = (Book) sut.getBook(book.getIsbn());
        assertEquals(wanted,have);
    }

    @Test
    public void updateBook() throws Exception {
        MediaService sut = new MediaServiceImplementation();
        sut.addBook(book);
        Book wanted = new Book("newTitle","newAuthor",book.getIsbn());
        sut.updateBook(wanted);
        Book have = (Book) sut.getBook(book.getIsbn());
        assertEquals(wanted,have);
    }


    @Test
    public void updateBookResultOK() throws Exception {
        MediaService sut = new MediaServiceImplementation();
        sut.addBook(book);
        Book newBook = new Book("newTitle","newAuthor",book.getIsbn());
        sut.updateBook(newBook);
        MediaServiceResult wanted = MediaServiceResult.OK;
        MediaServiceResult have = sut.updateBook(newBook);
        assertEquals(wanted,have);
    }

    @Test
    public void getBooks() throws Exception {
        MediaService sut = new MediaServiceImplementation();
        Book anotherBook = new Book("anotherTitle","anotherAuthor","978-3-551-55900-5");
        sut.addBook(book);
        sut.addBook(anotherBook);
        Medium[] wanted = {anotherBook,book}; // in this order, because it is sorted
        Medium[] have = sut.getBooks();
        assertArrayEquals(wanted,have);
    }



    @Test
    public void addDisc() throws Exception {

    }



    @Test
    public void updateDisc() throws Exception {

    }


    @Test
    public void getDiscs() throws Exception {

    }


    @Test
    public void getDisc() throws Exception {

    }

}