package edu.hm.shareit.mediaService;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * Representation of a MediaServiceResult.
 * Containing information: Status and Code
 */
public enum MediaServiceResult {
    OK(Status.OK, Response.status(Status.OK).build()),
    ILLEGAL_ISBN(Status.FORBIDDEN, Response.status(Status.FORBIDDEN).build()),
    ALREADY_EXISTS(Status.CONFLICT, Response.status(Status.NOT_FOUND).build()),
    MISSING_ARG(Status.BAD_REQUEST, Response.status(Status.BAD_REQUEST).build()),
    NOT_FOUND(Status.NOT_FOUND, Response.status(Status.NOT_FOUND).build()),
    FORBIDDEN(Status.FORBIDDEN, Response.status(Status.FORBIDDEN).build()),
    UNMATCHING_ISBN(Status.BAD_REQUEST, Response.status(Status.BAD_REQUEST).build());

    private Response.Status status;
    private Response response;

    /**
     * Constructor of MediaServiceResult.
     *
     * @param status   The status of the result
     * @param response The response of the result
     */
    MediaServiceResult(Status status, Response response) {
        this.status = status;
        this.response = response;
    }

    /**
     * Getter for response.
     *
     * @return The response
     */
    public Response getResponse() {
        return this.response;
    }

    /**
     * Getter for status.
     *
     * @return The status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Getter for the StatusCode.
     *
     * @return The StatusCode
     */
    public int getCode() {
        return getStatus().getStatusCode();
    }
}
