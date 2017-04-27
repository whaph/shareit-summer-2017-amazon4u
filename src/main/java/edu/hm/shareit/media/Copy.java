package edu.hm.shareit.media;

/** An exemplar of a medium bound to an owner.
 *
 */
public class Copy {

    private final Medium medium;
    private final String owner;

    /** Creates an exemplar of a medium and is bound to an owner.
     *
     * @param medium The medium
     * @param owner The owner
     */
    public Copy(Medium medium, String owner) {
        this.medium = medium;
        this.owner = owner;
    }

    /** Getter for medium.
     *
     * @return medium
     */
    public Medium getMedium() {
        return medium;
    }

    /** Getter for owner.
     *
     * @return owner
     */
    public String getOwner() {
        return owner;
    }
}