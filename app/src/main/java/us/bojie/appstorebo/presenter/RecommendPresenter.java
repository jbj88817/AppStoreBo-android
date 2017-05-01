package us.bojie.appstorebo.presenter;

import android.Manifest;
import android.app.Activity;

import com.tbruyelle.rxpermissions2.RxPermissions;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import us.bojie.appstorebo.bean.AppInfo;
import us.bojie.appstorebo.bean.PageBean;
import us.bojie.appstorebo.common.rx.RxHttpReponseCompat;
import us.bojie.appstorebo.common.rx.subscriber.ProgressSubscriber;
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

//    public void requestPermission(){
//        RxPermissions rxPermissions = new RxPermissions((Activity) mContext);
//        rxPermissions.request(Manifest.permission.READ_PHONE_STATE)
//                .subscribe(new Consumer<Boolean>() {
//                    @Override
//                    public void accept(Boolean aBoolean) throws Exception {
//                        if (aBoolean) {
//                            mView.onRequestPermissionSuccess();
//                        } else {
//                            mView.onRequestPermissionError();
//                        }
//                    }
//                });
//    }

    public void requestData() {

        RxPermissions rxPermissions = new RxPermissions((Activity) mContext);
        rxPermissions.request(Manifest.permission.READ_PHONE_STATE)
                .flatMap(new Function<Boolean, Observable<PageBean<AppInfo>>>() {
                    @Override
                    public Observable<PageBean<AppInfo>> apply(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            return mModel.getApps().compose(RxHttpReponseCompat.<PageBean<AppInfo>>compatResult());
                        } else {
                            mView.onRequestPermissionError();
                            return Observable.empty();
                        }
                    }
                }).subscribe(new ProgressSubscriber<PageBean<AppInfo>>(mContext, mView) {
                    @Override
                    public void onNext(PageBean<AppInfo> value) {
                        mView.showResult(value.getDatas());
                    }
                });

//        mModel.getApps()
//                .compose(RxHttpReponseCompat.<PageBean<AppInfo>>compatResult())
//                .subscribe(new ProgressSubscriber<PageBean<AppInfo>>(mContext, mView) {
//                    @Override
//                    public void onNext(PageBean<AppInfo> value) {
//                        mView.showResult(value.getDatas());
//                    }
//                });
    }
}
