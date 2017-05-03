package edu.hm.shareit.mediaService;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * Representation of a MediaServiceResult.
 * Containing information: Status and Code
 */
public enum MediaServiceResult {
    OK(Status.OK, Response.status(Status.OK), "Everything ok."),
    ILLEGAL_ISBN(Status.FORBIDDEN, Response.status(Status.FORBIDDEN), "ISBN is not written in ISBN-13 standard!"),
    ILLEGAL_BARCODE(Status.FORBIDDEN, Response.status(Status.FORBIDDEN), "Barcode is not written in EAN-Barcode-13 standard!"),
    ALREADY_EXISTS(Status.CONFLICT, Response.status(Status.NOT_FOUND), "Failed adding the Medium to the collection because it already exists."),
    MISSING_ARG(Status.BAD_REQUEST, Response.status(Status.BAD_REQUEST), "ISBN or Barcode missing!"),
    NOT_FOUND(Status.NOT_FOUND, Response.status(Status.NOT_FOUND), "Medium could not be found!"),
    FORBIDDEN(Status.FORBIDDEN, Response.status(Status.FORBIDDEN), "Null not allowed for operation!"),
    UNMATCHING_ISBN(Status.BAD_REQUEST, Response.status(Status.BAD_REQUEST), "No Book with that ISBN found"),
    UNMATCHING_BARCODE(Status.BAD_REQUEST, Response.status(Status.BAD_REQUEST), "No disc with that Barcode found");

    private final Response.Status status;
    private final Response response;
    private final MediaServiceResult.ErrorMessage reason = new ErrorMessage();

    /**
     * Constructor of MediaServiceResult.
     *
     * @param status   The status of the result
     * @param response The response of the result that will be build
     */
    MediaServiceResult(Status status, Response.ResponseBuilder response, String detail) {
        this.status = status;
        this.response = response.entity(reason(detail)).build();
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

    public Object reason(String detail) {
        return reason.reason(detail);
    }

    /** This class is the representation of the error message.
     * It shall be converted into a JSON object
     */
    class ErrorMessage {
        private int code = MediaServiceResult.this.getCode();
        private String detail;

        /** Given a reason it will build an error message as JSON object.
         *
         * @param detail The reason why the error occured
         * @return JSON object of the error message
         */
        public Object reason(String detail) {
            this.detail = detail;
            return convertToJson(this);
        }

        /** Converts an object into an JSON-Object representation.
         *
         * @param object The Target
         * @return JSON-Object representation
         */
        private String convertToJson(Object object) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                return mapper.writeValueAsString(object);
            } catch (Exception e) {
                System.out.println("MediaServiceResult >>> convertToJson() >> Error");
                return "";
            }
        }
    }
}
