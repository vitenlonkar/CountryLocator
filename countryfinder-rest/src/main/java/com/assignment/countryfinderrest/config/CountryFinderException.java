package com.assignment.countryfinderrest.config;

public class CountryFinderException extends Exception {

    private String errorCode;
    private String errorMessage;
    private Exception e;
    private String displayMessage;

    public CountryFinderException() {

    }

    public CountryFinderException(String errorCode, String errorMessage, String displayMessage, Exception e) {
       super();
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.e = e ;
        this.displayMessage = displayMessage;
    }
    public CountryFinderException( String errorMessage, String displayMessage, Exception e) {
        super();

        this.errorMessage = errorMessage;
        this.e = e ;
        this.displayMessage = displayMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Exception getE() {
        return e;
    }

    public void setE(Exception e) {
        this.e = e;
    }

    public String getDisplayMessage() {
        return displayMessage;
    }

    public void setDisplayMessage(String displayMessage) {
        this.displayMessage = displayMessage;
    }
}
