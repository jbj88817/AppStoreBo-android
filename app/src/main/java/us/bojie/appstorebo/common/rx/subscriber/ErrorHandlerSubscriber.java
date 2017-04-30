package us.bojie.appstorebo.common.rx.subscriber;

import android.content.Context;

import us.bojie.appstorebo.common.exception.BaseException;
import us.bojie.appstorebo.common.rx.RxErrorHandler;

/**
 * Created by bojiejiang on 4/26/17.
 */

public abstract class ErrorHandlerSubscriber<T> extends DefaultSubscriber<T> {

    protected RxErrorHandler mRxErrorHandler = null;
    private Context mContext;

    public ErrorHandlerSubscriber(Context context) {
        mContext = context;
        mRxErrorHandler = new RxErrorHandler(mContext);
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        BaseException exception = mRxErrorHandler.handleError(e);
        mRxErrorHandler.showErrorMessage(exception);
    }
}
