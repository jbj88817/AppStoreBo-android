package us.bojie.appstorebo.presenter;

import javax.inject.Inject;

import us.bojie.appstorebo.bean.AppInfo;
import us.bojie.appstorebo.bean.PageBean;
import us.bojie.appstorebo.common.rx.RxHttpReponseCompat;
import us.bojie.appstorebo.common.rx.subscriber.ProgressDialogSubscriber;
import us.bojie.appstorebo.data.RecommendModel;
import us.bojie.appstorebo.presenter.contract.RecommendContract;

/**
 * Created by bojiejiang on 4/23/17.
 */

public class RecommendPresenter extends BasePresenter<RecommendModel, RecommendContract.View> {


    @Inject
    public RecommendPresenter(RecommendModel model, RecommendContract.View view) {
        super(model, view);
    }


    public void requestData() {

        mModel.getApps()
                .compose(RxHttpReponseCompat.<PageBean<AppInfo>>compatResult())
                .subscribe(new ProgressDialogSubscriber<PageBean<AppInfo>>(mContext) {
                    @Override
                    public void onNext(PageBean<AppInfo> value) {
                        mView.showResult(value.getDatas());
                    }
                });
    }
}
