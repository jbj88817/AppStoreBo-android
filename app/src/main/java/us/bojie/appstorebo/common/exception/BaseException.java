package us.bojie.appstorebo.common.exception;

/**
 * Created by bojiejiang on 4/25/17.
 */

public class BaseException extends Exception {

    public static final int API_ERROR = 0x0;
    public static final int NETWORK_ERROR = 0x1;
    public static final int HTTP_ERROR = 0x2;
    public static final int JSON_ERROR = 0x3;
    public static final int UNKNOWN_ERROR = 0x4;
    public static final int RUNTIME_ERROR = 0x5;
    public static final int UNKOWNHOST_ERROR = 0x6;
    public static final int SOCKET_TIMEOUT_ERROR = 0x7;
    public static final int SOCKET_ERROR = 0x8;


    // http
    public static final int ERROR_HTTP_400 = 400;
    public static final int ERROR_HTTP_404 = 404;
    public static final int ERROR_HTTP_405 = 405;
    public static final int ERROR_HTTP_500 = 500;

    private int code;
    private String displayMessage;

    public BaseException() {
    }

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
