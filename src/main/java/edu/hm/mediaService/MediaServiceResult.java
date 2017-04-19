package edu.hm.mediaService;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * Representation of a MediaServiceResult
 * Containing information: Status and Code
 */
public enum MediaServiceResult {
    OK(Response.Status.OK,200),
    BOOK_NOT_FOUND(Response.Status.NOT_FOUND,404),
    NOT_A_MEDIUM(Status.BAD_REQUEST, 405);

    private Response.Status status;
    private int code;

    MediaServiceResult(Status status, int code) {
        this.status = status;
        this.code = code;
    }

    public int getCode() {
        return code;
    }


}
