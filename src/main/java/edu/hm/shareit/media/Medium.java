package edu.hm.shareit.media;

/**
 * Abstract class for media with title.
 */
public abstract class Medium {

    /**
     * Title of the medium.
     */
    private final String title;

    private Medium() {
        this.title = "These aren't the Droids you are looking for!";
    }

    public Medium(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Medium{" +
                "title='" + title + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }
}
