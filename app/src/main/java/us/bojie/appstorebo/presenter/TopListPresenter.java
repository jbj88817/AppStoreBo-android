package us.bojie.appstorebo.presenter;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import us.bojie.appstorebo.bean.AppInfo;
import us.bojie.appstorebo.bean.PageBean;
import us.bojie.appstorebo.common.rx.RxHttpReponseCompat;
import us.bojie.appstorebo.common.rx.subscriber.ErrorHandlerSubscriber;
import us.bojie.appstorebo.common.rx.subscriber.ProgressSubscriber;
import us.bojie.appstorebo.data.AppInfoModel;
import us.bojie.appstorebo.presenter.contract.AppInfoContract;

/**
 * Created by bojiejiang on 5/1/17.
 */

public class TopListPresenter extends BasePresenter<AppInfoModel, AppInfoContract.TopListView> {

    @Inject
    public TopListPresenter(AppInfoModel model, AppInfoContract.TopListView view) {
        super(model, view);
    }

    public void getTopListApps(int page) {

        Observer observer = null;

        if (page == 0) {
            observer = new ProgressSubscriber<PageBean<AppInfo>>(mContext, mView) {
                @Override
                public void onNext(PageBean<AppInfo> appInfoPageBean) {
                    mView.showResult(appInfoPageBean);
                }
            };
        } else {
            // Load next page
            observer = new ErrorHandlerSubscriber<PageBean<AppInfo>>(mContext) {

                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(PageBean<AppInfo> appInfoPageBean) {
                    mView.showResult(appInfoPageBean);
                }

                @Override
                public void onComplete() {
                    mView.onLoadMoreCompleted();
                }
            };
        }

        mModel.toplist(page)
                .compose(RxHttpReponseCompat.<PageBean<AppInfo>>compatResult())
                .subscribe(observer);
    }
}
