package edu.hm.mediaService;


import edu.hm.media.Book;
import edu.hm.media.Disc;
import edu.hm.media.Medium;

/**
 * Created by jupiter on 4/19/17.
 */
public interface MediaService {

    MediaServiceResult addBook(Book book);
    MediaServiceResult addDisc(Disc disc);
    Medium[] getBooks();
    Medium[] getDiscs();
    MediaServiceResult updateBook(Book book);
    MediaServiceResult updateDisc(Disc disc);

}
