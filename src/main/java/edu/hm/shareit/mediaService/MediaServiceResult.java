package edu.hm.shareit.mediaService;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * Representation of a MediaServiceResult
 * Containing information: Status and Code
 */
public enum MediaServiceResult {
    OK(Response.Status.OK, Response.ok().build()),
    NOT_FOUND(Response.Status.NOT_FOUND, Response.status(Status.NOT_FOUND).build()),
    ALREADY_EXISTS(Status.CONFLICT, Response.status(Status.NOT_FOUND).build()),
    FORBIDDEN(Status.FORBIDDEN, Response.status(Status.FORBIDDEN).build());


    private Response.Status status;
    private Response response;

    MediaServiceResult(Status status, Response response) {
        this.status = status;
        this.response = response;
    }

    public Response getResponse() {
        return this.response;
    }

}
