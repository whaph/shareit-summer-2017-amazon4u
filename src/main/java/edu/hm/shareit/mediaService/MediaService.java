package edu.hm.shareit.mediaService;


import edu.hm.shareit.media.Book;
import edu.hm.shareit.media.Disc;
import edu.hm.shareit.media.Medium;

/**
 * A MediaService interface for Books and Discs
 */
public interface MediaService {

    /**
     * Add a new Book with Author Title and an unique ISBN to the Books-list.
     * @param book
     * @return MediaServiceResult, whether the book was added successfully
     */
    MediaServiceResult addBook(Book book);

    /**
     * Add a new Disc with Director, Title, Fsk and a unique Barcode to the Discs-list.
     * @param disc
     * @return MediaServiceResult, whether the disc was added successfully
     */
    MediaServiceResult addDisc(Disc disc);

    /**
     * Get a Medium array that contains all books.
     * @return books
     */
    Medium[] getBooks();

    /**
     * Get a Medium array that contains all discs.
     * @return discs
     */
    Medium[] getDiscs();

    /**
     * Update Author and/or Title of a book with given ISBN.
     * @param book
     * @return whether the book was updated successfully
     */
    MediaServiceResult updateBook(Book book);

    /**
     * Update Director, Fsk and/or Title of a book with given Barcode.
     * @param disc
     * @return whether the disc was updated successfully
     */
    MediaServiceResult updateDisc(Disc disc);

    /** Get a Medium which is a book matching with the isbn.
     *
     * @param isbn The book that shall be returned
     * @return The requested book or null if not found.
     */
    Medium getBook(String isbn);

    /** Get a Medium which is a disc matching with the barcode.
     *
     * @param barcode The disc that shall be returned
     * @return The requested disc or null if not found.
     */
    Medium getDisc(String barcode);
}
