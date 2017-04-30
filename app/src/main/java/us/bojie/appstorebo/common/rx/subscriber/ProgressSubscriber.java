package us.bojie.appstorebo.common.rx.subscriber;

import android.content.Context;

import io.reactivex.disposables.Disposable;
import us.bojie.appstorebo.common.exception.BaseException;
import us.bojie.appstorebo.common.util.ProgressDialogHandler;
import us.bojie.appstorebo.ui.BaseView;

/**
 * Created by bojiejiang on 4/27/17.
 */

public abstract class ProgressSubscriber<T> extends ErrorHandlerSubscriber<T>
        implements ProgressDialogHandler.OnProgressCancelListener {

    private Disposable mDisposable;
    private ProgressDialogHandler mProgressDialogHandler;
    private BaseView mBaseView;

    public ProgressSubscriber(Context context, BaseView baseView) {
        super(context);
        mProgressDialogHandler = new ProgressDialogHandler(context, true, this);
        mBaseView = baseView;
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
            mBaseView.showLoading();
        }
    }

    @Override
    public void onComplete() {
        mBaseView.dismissLoading();
    }

    @Override
    public void onError(Throwable e) {
        BaseException exception = mRxErrorHandler.handleError(e);
        mBaseView.showError(exception.getDisplayMessage());
    }

//    private void initProgressDialog() {
//        if (mProgressDialog == null) {
//            mProgressDialog = new ProgressDialog(mContext);
//            mProgressDialog.setMessage("Loading....");
//        }
//    }

}
