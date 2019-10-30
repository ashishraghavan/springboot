package org.ashish.springboot.practice.exception;

import com.google.common.collect.ImmutableMap;

@SuppressWarnings("unused")
public class BootException extends Exception {
    final ImmutableMap<Integer,String> errorCodeMap = ImmutableMap.<Integer,String>builder()
            .put(400,"The request could not be understood by the server because %s")
            .put(501,"The server does not support the functionality required to fulfill the request")
            .build();

    private Exception exception;
    private Integer statusCode;
    private String message;
    public BootException() {super();}
    public BootException(Exception exception,Integer httpStatus) {
        this.exception = exception;
        this.statusCode = httpStatus;
        this.message=String.format(errorCodeMap.get(httpStatus),exception.getLocalizedMessage());
    }

    public Exception getException() {
        return exception;
    }

    private void setException(Exception exception) {
        this.exception = exception;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
