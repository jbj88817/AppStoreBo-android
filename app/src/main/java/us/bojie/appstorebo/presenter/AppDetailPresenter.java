package us.bojie.appstorebo.presenter;

import javax.inject.Inject;

import us.bojie.appstorebo.bean.AppInfo;
import us.bojie.appstorebo.common.rx.RxHttpReponseCompat;
import us.bojie.appstorebo.common.rx.subscriber.ProgressSubscriber;
import us.bojie.appstorebo.data.AppInfoModel;
import us.bojie.appstorebo.presenter.contract.AppInfoContract;

/**
 * Created by bojiejiang on 5/10/17.
 */

public class AppDetailPresenter extends BasePresenter<AppInfoModel, AppInfoContract.AppDetailView> {


    @Inject
    public AppDetailPresenter(AppInfoModel model, AppInfoContract.AppDetailView view) {
        super(model, view);
    }

    public void getAppDetail(int id) {
        mModel.getAppDetail(id)
                .compose(RxHttpReponseCompat.<AppInfo>compatResult())
                .subscribe(new ProgressSubscriber<AppInfo>(mContext, mView) {
                    @Override
                    public void onNext(AppInfo value) {
                        mView.showAppDetail(value);
                    }
                });
    }
}
