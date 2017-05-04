package us.bojie.appstorebo.common.rx.subscriber;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import us.bojie.appstorebo.common.exception.ApiException;
import us.bojie.appstorebo.common.exception.BaseException;
import us.bojie.appstorebo.common.rx.RxErrorHandler;
import us.bojie.appstorebo.ui.activity.LoginActivity;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

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
        if (exception == null) {
            e.printStackTrace();
            Log.d(TAG, "ErrorHandlerSubscriber: " + e.getMessage());
        } else {
            mRxErrorHandler.showErrorMessage(exception);
            if (exception.getCode() == ApiException.ERROR_TOKEN) {
                toLogin();
            }
        }
    }

    private void toLogin() {
        mContext.startActivity(new Intent(mContext, LoginActivity.class));
    }
}
