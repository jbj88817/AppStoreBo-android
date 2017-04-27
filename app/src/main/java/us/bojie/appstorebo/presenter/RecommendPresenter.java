package us.bojie.appstorebo.presenter;

import android.util.Log;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import us.bojie.appstorebo.bean.AppInfo;
import us.bojie.appstorebo.bean.PageBean;
import us.bojie.appstorebo.common.rx.RxHttpReponseCompat;
import us.bojie.appstorebo.data.RecommendModel;
import us.bojie.appstorebo.presenter.contract.RecommendContract;

import static com.mikepenz.iconics.Iconics.TAG;

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
                .subscribe(new Observer<PageBean<AppInfo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mView.showLoading();
                    }

                    @Override
                    public void onNext(PageBean<AppInfo> appInfoPageBean) {
                        if (appInfoPageBean != null) {
                            mView.showResult(appInfoPageBean.getDatas());
                        } else {
                            mView.showNoData();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "RecommendPresenter: ------");
                    }

                    @Override
                    public void onComplete() {
                        mView.dismissLoading();
                    }
                });

//        mModel.getApps(new Callback<PageBean<AppInfo>>() {
//            @Override
//            public void onResponse(Call<PageBean<AppInfo>> call, Response<PageBean<AppInfo>> response) {
//                if (response != null) {
//                    mView.showResult(response.body().getDatas());
//                } else {
//                    mView.showNoData();
//                }
//
//                mView.dismissLoading();
//            }
//
//            @Override
//            public void onFailure(Call<PageBean<AppInfo>> call, Throwable t) {
//                mView.dismissLoading();
//                mView.showError(t.getMessage());
//            }
//        });
    }
}
