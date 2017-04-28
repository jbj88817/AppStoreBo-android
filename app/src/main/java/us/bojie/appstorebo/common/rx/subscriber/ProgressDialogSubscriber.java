package us.bojie.appstorebo.common.rx.subscriber;

import android.content.Context;

import io.reactivex.disposables.Disposable;
import us.bojie.appstorebo.common.util.ProgressDialogHandler;

/**
 * Created by bojiejiang on 4/27/17.
 */

public abstract class ProgressDialogSubscriber<T> extends ErrorHandlerSubscriber<T>
        implements ProgressDialogHandler.OnProgressCancelListener {

    private Disposable mDisposable;
    private ProgressDialogHandler mProgressDialogHandler;

    public ProgressDialogSubscriber(Context context) {
        super(context);
        mProgressDialogHandler = new ProgressDialogHandler(context, true, this);

    }

    protected boolean isShowDialog() {
        return true;
    }

    @Override
    public void onCancelProgress() {
        mDisposable.dispose();
    }

    @Override
    public void onSubscribe(Disposable d) {
        mDisposable = d;
        if (isShowDialog()) {
            mProgressDialogHandler.showProgressDialog();
        }
    }

    @Override
    public void onComplete() {
        if (isShowDialog()) {
            mProgressDialogHandler.dismissProgressDialog();
        }
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        if (isShowDialog()) {
            mProgressDialogHandler.dismissProgressDialog();
        }
    }

//    private void initProgressDialog() {
//        if (mProgressDialog == null) {
//            mProgressDialog = new ProgressDialog(mContext);
//            mProgressDialog.setMessage("Loading....");
//        }
//    }

}
