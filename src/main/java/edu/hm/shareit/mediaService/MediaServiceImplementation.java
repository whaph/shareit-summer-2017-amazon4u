package edu.hm.shareit.mediaService;

import edu.hm.shareit.media.Book;
import edu.hm.shareit.media.Disc;
import edu.hm.shareit.media.Medium;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by jupiter on 4/19/17.
 */
public class MediaServiceImplementation implements MediaService{
    final Collection<Book> books = new HashSet<>();
    final Collection<Disc> discs = new HashSet<>();

    @Override
    public MediaServiceResult addBook(Book book) {
        if(book == null)
            return MediaServiceResult.FORBIDDEN;

        if(!getBooksCollection().contains(book)){
            return MediaServiceResult.OK;
        }

        getBooksCollection().add(book);
        return MediaServiceResult.OK;
    }

    @Override
    public MediaServiceResult addDisc(Disc disc) {
        if(disc == null)
            return MediaServiceResult.FORBIDDEN;

        if(!getDiscsCollection().contains(disc)){
            return MediaServiceResult.OK;
        }

        getDiscsCollection().add(disc);
        return MediaServiceResult.OK;
    }

    @Override
    public MediaServiceResult updateBook(Book book) {
        if(book == null){
            return MediaServiceResult.FORBIDDEN;
        }
        final boolean removed = getBooksCollection().remove(book);
        if(removed){
            addBook(book);
            return MediaServiceResult.OK;
        }
        else {
            return MediaServiceResult.NOT_FOUND;
        }
    }

    @Override
    public MediaServiceResult updateDisc(Disc disc) {
        if(disc == null){
            return MediaServiceResult.FORBIDDEN;
        }
        final boolean removed = getDiscsCollection().remove(disc);
        if(removed){
            addDisc(disc);
            return MediaServiceResult.OK;
        }
        else {
            return MediaServiceResult.NOT_FOUND;
        }
    }

    @Override
    public Medium[] getBooks() {
        return (Medium[]) getBooksCollection().stream().sorted().toArray();
    }

    @Override
    public Medium[] getDiscs() {
        return (Medium[]) getDiscsCollection().stream().sorted().toArray();
    }

    private Collection<Book> getBooksCollection(){
        return this.books;
    }

    private Collection<Disc> getDiscsCollection(){
        return this.discs;
    }
}
