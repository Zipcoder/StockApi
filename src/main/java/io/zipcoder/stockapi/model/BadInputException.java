package io.zipcoder.stockapi.model;

public class BadInputException {
    private static final String exceptionType = "BadInputException";
    private String errorMessage;

    public BadInputException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public static String getExceptionType() {
        return exceptionType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
