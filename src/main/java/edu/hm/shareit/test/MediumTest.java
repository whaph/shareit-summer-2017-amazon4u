package edu.hm.shareit.test;

import edu.hm.shareit.media.Book;
import edu.hm.shareit.media.Medium;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jupiter on 4/29/17.
 */
public class MediumTest {

    @Test
    public void equals() throws Exception {
        Medium sut = new Book();
        Medium sut2 = new Book();
        assertEquals(sut,sut2);
    }

}