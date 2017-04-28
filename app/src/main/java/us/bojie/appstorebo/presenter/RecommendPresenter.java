package us.bojie.appstorebo.presenter;

import javax.inject.Inject;

import us.bojie.appstorebo.bean.AppInfo;
import us.bojie.appstorebo.bean.PageBean;
import us.bojie.appstorebo.common.rx.RxErrorHandler;
import us.bojie.appstorebo.common.rx.RxHttpReponseCompat;
import us.bojie.appstorebo.common.rx.subscriber.ProgressDialogSubcriber;
import us.bojie.appstorebo.data.RecommendModel;
import us.bojie.appstorebo.presenter.contract.RecommendContract;

/**
 * Created by bojiejiang on 4/23/17.
 */

public class RecommendPresenter extends BasePresenter<RecommendModel, RecommendContract.View> {

    private RxErrorHandler mRxErrorHandler;


    @Inject
    public RecommendPresenter(RecommendModel model, RecommendContract.View view, RxErrorHandler errorHandler) {
        super(model, view);
        mRxErrorHandler = errorHandler;
    }


    public void requestData() {

        mModel.getApps()
                .compose(RxHttpReponseCompat.<PageBean<AppInfo>>compatResult())
                .subscribe(new ProgressDialogSubcriber<PageBean<AppInfo>>(mView, mRxErrorHandler) {
                    @Override
                    public void onNext(PageBean<AppInfo> value) {
                        mView.showResult(value.getDatas());
                    }
                });
    }
}
