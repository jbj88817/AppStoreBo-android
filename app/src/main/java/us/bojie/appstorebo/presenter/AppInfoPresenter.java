package us.bojie.appstorebo.presenter;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import us.bojie.appstorebo.bean.AppInfo;
import us.bojie.appstorebo.bean.BaseBean;
import us.bojie.appstorebo.bean.PageBean;
import us.bojie.appstorebo.common.rx.RxHttpReponseCompat;
import us.bojie.appstorebo.common.rx.subscriber.ErrorHandlerSubscriber;
import us.bojie.appstorebo.common.rx.subscriber.ProgressSubscriber;
import us.bojie.appstorebo.data.AppInfoModel;
import us.bojie.appstorebo.presenter.contract.AppInfoContract;

/**
 * Created by bojiejiang on 5/1/17.
 */

public class AppInfoPresenter extends BasePresenter<AppInfoModel, AppInfoContract.AppInfoView> {

    public static final int TOP_LIST = 1;
    public static final int GAMES = 2;
    public static final int CATEGORY = 3;

    public static final int FEATURED = 0;
    public static final int TOPLIST = 1;
    public static final int NEWLIST = 2;

    @Inject
    public AppInfoPresenter(AppInfoModel model, AppInfoContract.AppInfoView view) {
        super(model, view);
    }

    private void request(int type, int page, int categoryId, int fragmentType) {
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

        Observable observable = getObservable(type, page, categoryId, fragmentType);
        observable
                .compose(RxHttpReponseCompat.<PageBean<AppInfo>>compatResult())
                .subscribe(observer);
    }

    public void requestData(int type, int page) {
        request(type, page, 0, 0);
    }

    public void requestWithCategory(int page, int categoryId, int fragmentType) {
        request(AppInfoPresenter.CATEGORY, page, categoryId, fragmentType);
    }

    private Observable<BaseBean<PageBean<AppInfo>>> getObservable(int type, int page, int categoryId, int fragmentType) {
        switch (type) {
            case TOP_LIST:
                return mModel.toplist(page);
            case GAMES:
                return mModel.games(page);
            case CATEGORY:
                if (fragmentType == FEATURED) {
                    return mModel.getFeaturedAppsByCategory(categoryId, page);
                } else if (fragmentType == TOPLIST) {
                    return mModel.getTopListAppsByCategory(categoryId, page);
                } else if (fragmentType == NEWLIST) {
                    return mModel.getNewListAppsByCategory(categoryId, page);
                }

            default:
                return Observable.empty();
        }
    }
}
