package edu.hm.shareit.media;

/**
 * A Class for Discs.
 */
public class Disc extends Medium{
    /**
     * Barcode of Disc.
     */
    final String barcode;
    /**
     * Director of Disc.
     */
    final String director;
    /**
     * Fsk of Disc.
     */
    final int fsk;

    /**
     *  <a href="https://www.youtube.com/watch?v=oHg5SJYRHA0">Click me</a>
     */
    public Disc() {
        this("Never gonna", "give you", "up",0);
    }

    public Disc(String title, String barcode, String director, int fsk) {
        super(title);
        this.barcode = barcode;
        this.director = director;
        this.fsk = fsk;
    }

    public String getBarcode(){
        return this.barcode;
    }

    public String getDirector() {
        return director;
    }

    public int getFsk() {
        return fsk;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Disc disc = (Disc) o;

        return getBarcode().equals(disc.getBarcode());
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getBarcode().hashCode();
        return result;
    }
}
