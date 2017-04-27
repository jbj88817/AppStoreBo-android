package us.bojie.appstorebo.common.rx.subscriber;

import us.bojie.appstorebo.common.exception.BaseException;
import us.bojie.appstorebo.common.rx.RxErrorHandler;

/**
 * Created by bojiejiang on 4/26/17.
 */

public abstract class ErrorHandlerSubscriber<T> extends DefaultSubscriber<T> {

    private RxErrorHandler mRxErrorHandler;

    public ErrorHandlerSubscriber(RxErrorHandler errorHandler) {
        mRxErrorHandler = errorHandler;
    }

    @Override
    public void onError(Throwable e) {
        BaseException exception = mRxErrorHandler.handleError(e);
        mRxErrorHandler.showErrorMessage(exception);
    }
}
