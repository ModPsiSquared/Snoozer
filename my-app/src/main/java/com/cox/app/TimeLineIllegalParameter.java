package com.cox.app;


public class TimeLineIllegalParameter extends RuntimeException {
    protected String message;
    public TimeLineIllegalParameter(String message) {
        this.message = message;
    }
}
