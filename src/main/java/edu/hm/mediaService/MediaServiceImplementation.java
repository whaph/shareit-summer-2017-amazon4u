package edu.hm.mediaService;

import edu.hm.media.Book;
import edu.hm.media.Disc;
import edu.hm.media.Medium;

/**
 * Created by jupiter on 4/19/17.
 */
public class MediaServiceImplementation implements MediaService{



    @Override
    public MediaServiceResult addBook(Book book) {
        return null;
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
