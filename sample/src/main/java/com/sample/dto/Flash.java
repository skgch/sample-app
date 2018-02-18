package com.sample.dto;

public class Flash {

    private boolean successFlag;
    private String message;

    public Flash(boolean successFlag, String message) {
        this.successFlag = successFlag;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean getSuccessFlag() {
        return successFlag;
    }

    public void setSuccessFlag(boolean successFlag) {
        this.successFlag = successFlag;
    }

}
