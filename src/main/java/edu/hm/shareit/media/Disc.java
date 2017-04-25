package edu.hm.shareit.media;

/**
 * Created by jupiter on 4/19/17.
 */
public class Disc extends Medium{
    final String barcode;
    final String director;
    final int fsk;

    public Disc() {
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

        if (getFsk() != disc.getFsk()) return false;
        if (!getBarcode().equals(disc.getBarcode())) return false;
        return getDirector().equals(disc.getDirector());

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getBarcode().hashCode();
        result = 31 * result + getDirector().hashCode();
        result = 31 * result + getFsk();
        return result;
    }
}
