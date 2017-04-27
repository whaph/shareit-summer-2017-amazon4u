package edu.hm.shareit.mediaService;

import edu.hm.shareit.media.Book;
import edu.hm.shareit.media.Disc;
import edu.hm.shareit.media.Medium;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Stream;

/**
 * Created by jupiter on 4/19/17.
 */
public class MediaServiceImplementation implements MediaService {
    private static final Collection<Book> books = new HashSet<>();
    private static final Collection<Disc> discs = new HashSet<>();

    @Override
    public MediaServiceResult addBook(Book book) {
        if (book == null)
            return MediaServiceResult.FORBIDDEN;

        if (book.getAuthor() == "" || book.getTitle() == "") {
            System.out.println("MediaServiceResult >>> addBook() -> author or title missing");
            return MediaServiceResult.MISSING_ARG;
        }

        if (!isValidISBN(book.getIsbn())) {
            System.out.println("MediaServiceResult >>> addBook() -> Illegal ISBN");
            return MediaServiceResult.ILLEGAL_ISBN;
        }

        if (getBooksCollection().contains(book)) {
            System.out.println("MediaServiceResult >>> addBook() -> Duplicate found");
            return MediaServiceResult.ALREADY_EXISTS;
        }

        getBooksCollection().add(book);
        System.out.println("MediaServiceResult >>> addBook() -> book has been added");
        System.out.println("MediaServiceResult >>> addBook() -> current size "
                + getBooksCollection().size());

        return MediaServiceResult.OK;
    }

    @Override
    public MediaServiceResult addDisc(Disc disc) {
        if (disc == null)
            return MediaServiceResult.FORBIDDEN;

        if (disc.getDirector() == "" || disc.getTitle() == "") {
            System.out.println("MediaServiceResult >>> addBook() -> director or title missing");
            return MediaServiceResult.MISSING_ARG;
        }

        if (!isValidBarcode(disc.getBarcode())) {
            System.out.println("MediaServiceResult >>> addBook() -> Illegal ISBN");
            return MediaServiceResult.ILLEGAL_ISBN;
        }

        if (getDiscsCollection().contains(disc)) {
            return MediaServiceResult.ALREADY_EXISTS;
        }

        getDiscsCollection().add(disc);
        return MediaServiceResult.OK;
    }

    @Override
    public MediaServiceResult updateBook(Book book) {
        if (book == null) {
            return MediaServiceResult.FORBIDDEN;
        }
        Book toBeUpdated = (Book)getBook(book.getIsbn());

        if(toBeUpdated == null){
            return MediaServiceResult.UNMATCHING_ISBN;
        }
        toBeUpdated.setAuthor(book.getAuthor());
        toBeUpdated.setTitle(book.getTitle());
        return MediaServiceResult.OK;
    }

    @Override
    public MediaServiceResult updateDisc(Disc disc) {
        if (disc == null) {
            return MediaServiceResult.FORBIDDEN;
        }
        Disc toBeUpdated = (Disc)getDisc(disc.getBarcode());

        if(toBeUpdated == null){
            return MediaServiceResult.UNMATCHING_ISBN;
        }
        toBeUpdated.setTitle(disc.getTitle());
        toBeUpdated.setDirector(disc.getDirector());
        toBeUpdated.setFsk(disc.getFsk());
        return MediaServiceResult.OK;
    }

    @Override
    public Medium[] getBooks() {
        return getBooksCollection().toArray(new Medium[0]);
    }

    @Override
    public Medium[] getDiscs() {
        return getDiscsCollection().toArray(new Medium[0]);
    }

    @Override
    public Medium getBook(String isbn) {
        for(Book book: getBooksCollection()){
            if(book.getIsbn().equals(isbn))
                return book;
        }
        return null;
    }

    @Override
    public Medium getDisc(String barcode){
        for(Disc disc: getDiscsCollection()){
            if(disc.getBarcode().equals(barcode))
                return disc;
        }
        return null;
    }

    private Collection<Book> getBooksCollection() {
        return books;
    }

    private Collection<Disc> getDiscsCollection() {
        return discs;
    }

    private boolean isValidISBN(String isbn) {
        if (isbn == null) return false;

        isbn = isbn.replaceAll("-", "");

        final int maxLength = 13;
        if (isbn.length() != maxLength) return false;

        int sum = 0;
        for (int index = 0; index < maxLength - 1; index++) {
            int digit = Integer.parseInt(isbn.substring(index, index + 1));
            sum += (index % 2 == 0) ? digit : digit * 3;
        }

        int checksum = 10 - (sum % 10);
        if (checksum == 10) {
            checksum = 0;
        }

        return checksum == Integer.parseInt(isbn.substring(12));
    }

    private boolean isValidBarcode(String barcode) {
        return barcode.length() == 13 && barcode.matches("[0-9]+");
    }
}
