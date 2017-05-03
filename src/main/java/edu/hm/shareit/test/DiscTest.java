package edu.hm.shareit.test;

import edu.hm.shareit.media.Book;
import edu.hm.shareit.media.Disc;
import org.junit.Test;

import static org.junit.Assert.*;

public class DiscTest {

    @Test
    public void equalsTest() throws Exception {
        Disc sut1 = new Disc("title","author","1231231231231",0);
        Disc sut2 = new Disc("title2","author2","1231231231231",0);
        assertEquals(sut1,sut2);
    }

    @Test
    public void equalsTestNull() throws Exception {
        Disc sut = new Disc("title","author","1231231231231",0);
        assertFalse(sut.equals(null));
    }

    @Test
    public void equalsTestDisc() throws Exception {
        Disc sut = new Disc("title","director","1231231231231",0);
        Book book = new Book("title","author","978-3551551672");
        assertFalse(sut.equals(book));
    }

}