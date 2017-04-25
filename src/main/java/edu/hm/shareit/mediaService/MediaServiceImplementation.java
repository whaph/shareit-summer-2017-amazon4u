package edu.hm.shareit.mediaService;

import edu.hm.shareit.media.Book;
import edu.hm.shareit.media.Disc;
import edu.hm.shareit.media.Medium;

import java.util.ArrayList;

/**
 * Created by jupiter on 4/19/17.
 */
public class MediaServiceImplementation implements MediaService{
    final ArrayList<Book> books = new ArrayList<>();
    final ArrayList<Disc> discs = new ArrayList<>();

    private ArrayList<Book> getBookList(){
        return this.books;
    }
    private ArrayList<Disc> getDiscList(){
        return this.discs;
    }

    @Override
    public MediaServiceResult addBook(Book book) {
        if(book == null)
            return MediaServiceResult.NOT_A_MEDIUM;
        getBookList().add(book);
        return MediaServiceResult.OK;
    }

    @Override
    public MediaServiceResult addDisc(Disc disc) {
        return null;
    }

    @Override
    public Medium[] getBooks() {
        return new Medium[0];
    }

    @Override
    public Medium[] getDiscs() {
        return new Medium[0];
    }

    @Override
    public MediaServiceResult updateBook(Book book) {
        return null;
    }

    @Override
    public MediaServiceResult updateDisc(Disc disc) {
        return null;
    }
}
