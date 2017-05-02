package us.bojie.appstorebo.presenter;

import javax.inject.Inject;

import us.bojie.appstorebo.bean.IndexBean;
import us.bojie.appstorebo.common.rx.RxHttpReponseCompat;
import us.bojie.appstorebo.common.rx.subscriber.ProgressSubscriber;
import us.bojie.appstorebo.data.AppInfoModel;
import us.bojie.appstorebo.presenter.contract.AppInfoContract;

/**
 * Created by bojiejiang on 4/23/17.
 */

public class RecommendPresenter extends BasePresenter<AppInfoModel, AppInfoContract.View> {


    @Inject
    public RecommendPresenter(AppInfoModel model, AppInfoContract.View view) {
        super(model, view);
    }

    public void requestData(){
        mModel.index().compose(RxHttpReponseCompat.<IndexBean>compatResult())
                .subscribe(new ProgressSubscriber<IndexBean>(mContext, mView) {
                    @Override
                    public void onNext(IndexBean indexBean) {
                        mView.showResult(indexBean);
                    }
                });
    }
}
