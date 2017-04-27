package edu.hm.shareit.media;

/**
 * Created by jupiter on 4/19/17.
 */
public class Book extends Medium {
    private String author;
    private final String isbn;

    /**
     * Default constructor for Jackson
     */
    private Book() {
        this("empty", "empty", "empty");
    }

    /**
     * Creates a representation of a book.
     * Not to be mistaken as an book exemplar.
     *
     * @param title  Title of the book
     * @param author Author(s) of the book
     * @param isbn   ISBN of the book
     */
    public Book(String title, String author, String isbn) {
        super(title);
        this.author = author;
        this.isbn = isbn;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Getter for author.
     *
     * @return author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Getter for ISBN.
     *
     * @return isbn
     */
    public String getIsbn() {
        return isbn;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        Book book = (Book) o;

        return getIsbn().equals(book.getIsbn());
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getIsbn().hashCode();
        return result;
    }
}
