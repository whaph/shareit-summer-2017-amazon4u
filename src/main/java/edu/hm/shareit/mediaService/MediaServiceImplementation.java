package edu.hm.shareit.mediaService;

import edu.hm.shareit.media.Book;
import edu.hm.shareit.media.Disc;
import edu.hm.shareit.media.Medium;

import java.util.Collection;
import java.util.HashSet;

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
            System.out.println("MediaServiceResult >>> addDisc() -> director or title missing");
            return MediaServiceResult.MISSING_ARG;
        }

        if (!isValidBarcode(disc.getBarcode())) {
            System.out.println("MediaServiceResult >>> addDisc() -> Illegal ISBN");
            return MediaServiceResult.ILLEGAL_ISBN;
        }

        if (getDiscsCollection().contains(disc)) {
            System.out.println("MediaServiceResult >>> addDisc() -> Duplicate found");
            return MediaServiceResult.ALREADY_EXISTS;
        }

        getDiscsCollection().add(disc);
        System.out.println("MediaServiceResult >>> addDisc() -> disc has been added");
        System.out.println("MediaServiceResult >>> addDisc() -> current size "
                + getDiscsCollection().size());
        return MediaServiceResult.OK;
    }

    @Override
    public MediaServiceResult updateBook(Book book) {
        if (book == null) {
            return MediaServiceResult.FORBIDDEN;
        }
        final boolean removed = getBooksCollection().remove(book);
        if (removed) {
            addBook(book);
            return MediaServiceResult.OK;
        } else {
            return MediaServiceResult.NOT_FOUND;
        }
    }

    @Override
    public MediaServiceResult updateDisc(Disc disc) {
        if (disc == null) {
            return MediaServiceResult.FORBIDDEN;
        }
        final boolean removed = getDiscsCollection().remove(disc);
        if (removed) {
            addDisc(disc);
            return MediaServiceResult.OK;
        } else {
            return MediaServiceResult.NOT_FOUND;
        }
    }

    @Override
    public Medium[] getBooks() {
        return getBooksCollection().toArray(new Medium[0]);
    }

    @Override
    public Medium[] getDiscs() {
        return getDiscsCollection().toArray(new Medium[0]);
    }

    private Collection<Book> getBooksCollection() {
        return this.books;
    }

    private Collection<Disc> getDiscsCollection() {
        return this.discs;
    }

    private boolean isValidISBN(String isbn) {
        if (isbn == null) return false;

        isbn = isbn.replaceAll("-", "");

        final int maxLength = 13;
        if (isbn.length() != maxLength) return false;

        int sum = 0;
        for (int index = 0; index < maxLength - 1; index++) {
            int digit = Integer.parseInt(isbn.substring(index, index + 1));
            if (index % 2 == 0) sum += digit;
            else sum += digit * 3;
        }

        int checksum = 10 - (sum % 10);
        if (checksum == 10) {
            checksum = 0;
        }

        return checksum == Integer.parseInt(isbn.substring(12));
    }

    private boolean isValidBarcode(String barcode) {
        return isValidISBN(barcode);
    }
}
