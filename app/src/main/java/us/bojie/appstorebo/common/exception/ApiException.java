package us.bojie.appstorebo.common.exception;

/**
 * Created by bojiejiang on 4/25/17.
 */

public class ApiException extends BaseException {

    public ApiException(int code, String displayMessage) {
        super(code, displayMessage);
    }
}
