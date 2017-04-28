package us.bojie.appstorebo.common.rx.subscriber;

import android.app.ProgressDialog;

import io.reactivex.disposables.Disposable;
import us.bojie.appstorebo.common.rx.RxErrorHandler;
import us.bojie.appstorebo.ui.BaseView;

/**
 * Created by bojiejiang on 4/27/17.
 */

public abstract class ProgressDialogSubcriber<T> extends ErrorHandlerSubscriber<T> {

    private BaseView mBaseView;
    private ProgressDialog mProgressDialog;

    public ProgressDialogSubcriber(BaseView view, RxErrorHandler rxErrorHandler) {
        super(rxErrorHandler);
        mBaseView = view;
    }

    protected boolean isShowDialog() {
        return true;
    }

    @Override
    public void onSubscribe(Disposable d) {
        if (isShowDialog()) {
            showProgressDialog();
        }
    }

    @Override
    public void onComplete() {
        if (isShowDialog()) {
            dismissProgressDialog();
        }
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        if (isShowDialog()) {
            dismissProgressDialog();
        }
    }

//    private void initProgressDialog() {
//        if (mProgressDialog == null) {
//            mProgressDialog = new ProgressDialog(mContext);
//            mProgressDialog.setMessage("Loading....");
//        }
//    }

    private void showProgressDialog() {
        mBaseView.showLoading();
    }

    private void dismissProgressDialog() {
        mBaseView.dismissLoading();
    }
}
