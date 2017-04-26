package edu.hm.shareit.media;

/**
 * Created by jupiter on 4/19/17.
 */
public class Disc extends Medium{
    private final String barcode;
    private final String director;
    private final int fsk;

    private Disc() {
        this("empty", "empty", "empty",0);
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
