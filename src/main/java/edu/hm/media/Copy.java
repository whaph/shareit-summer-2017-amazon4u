package edu.hm.media;

/** An exemplar of a medium bound to an owner.
 *
 */
public class Copy {

    private final Medium medium;
    private final String owner;

    public Copy(Medium medium, String owner) {
        this.medium = medium;
        this.owner = owner;
    }

    public Medium getMedium() {
        return medium;
    }

    public String getOwner() {
        return owner;
    }
}