package edu.hm.shareit.mediaService;
import javax.naming.spi.ResolveResult;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * Representation of a MediaServiceResult
 * Containing information: Status and Code
 */
public enum MediaServiceResult {
    OK              (Status.OK,             Response.status(Status.OK).build()),
    ILLEGAL_ISBN    (Status.FORBIDDEN,      Response.status(Status.FORBIDDEN).build()),
    ALREADY_EXISTS  (Status.CONFLICT,       Response.status(Status.NOT_FOUND).build()),
    MISSING_ARG     (Status.BAD_REQUEST,    Response.status(Status.BAD_REQUEST).build()),
    NOT_FOUND       (Status.NOT_FOUND,      Response.status(Status.NOT_FOUND).build()),
    FORBIDDEN       (Status.FORBIDDEN,      Response.status(Status.FORBIDDEN).build()),
    UNMATCHING_ISBN (Status.BAD_REQUEST,    Response.status(Status.BAD_REQUEST).build());

    private Response.Status status;
    private Response response;

    MediaServiceResult(Status status, Response response) {
        this.status = status;
        this.response = response;
    }

    public Response getResponse() {
        return this.response;
    }

    public Status getStatus() {
        return status;
    }

    public int getCode(){
        return getStatus().getStatusCode();
    }
}
