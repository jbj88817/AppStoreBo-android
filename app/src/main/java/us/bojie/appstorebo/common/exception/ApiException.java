package us.bojie.appstorebo.common.exception;

/**
 * Created by bojiejiang on 4/25/17.
 */

public class ApiException extends BaseException {

    // api
    public static final int ERROR_API_SYSTEM = 10000;
    public static final int ERROR_API_LOGIN = 10001;
    public static final int ERROR_API_NO_PERMISSION = 10002;
    public static final int ERROR_API_ACCOUNT_FREEZE = 10003;
    public static final int ERROR_TOKEN = 10010;


    public ApiException(int code, String displayMessage) {
        super(code, displayMessage);
    }
}
