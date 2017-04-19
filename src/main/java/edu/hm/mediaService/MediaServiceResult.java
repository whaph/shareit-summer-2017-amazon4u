package edu.hm.mediaService;
import  edu.hm.REST.status.Status;

/**
 * Representation of a MediaServiceResult
 * Containing information: Status and Code
 */
public enum MediaServiceResult {

    /*
    *  TODO - Create some values
    */
    ;

    private final int code;
    private final Status status;

    MediaServiceResult(int code, Status status){
        this.code = code;
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public Status getStatus() {
        return status;
    }
}
