package edu.hm.shareit.media;

/**
 * Created by jupiter on 4/19/17.
 */
public class Book extends Medium{
    private final String author;
    private final String isbn;

    private Book() {
        this("empty", "empty", "empty");
    }

    public Book(String title, String author, String isbn) {
        super(title);
        this.author = author;
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

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
