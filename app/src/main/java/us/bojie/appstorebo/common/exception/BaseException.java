package us.bojie.appstorebo.common.exception;

/**
 * Created by bojiejiang on 4/25/17.
 */

public class BaseException extends Exception {
    private int code;
    private String displayMessage;

    public BaseException(int code, String displayMessage) {
        this.code = code;
        this.displayMessage = displayMessage;
    }

    public BaseException(String message, int code, String displayMessage) {
        super(message);
        this.code = code;
        this.displayMessage = displayMessage;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDisplayMessage() {
        return displayMessage;
    }

    public void setDisplayMessage(String displayMessage) {
        this.displayMessage = displayMessage;
    }
}
