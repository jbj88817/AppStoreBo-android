package us.bojie.appstorebo.presenter;

import com.hwangjr.rxbus.RxBus;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import us.bojie.appstorebo.bean.LoginBean;
import us.bojie.appstorebo.common.Constant;
import us.bojie.appstorebo.common.rx.RxHttpReponseCompat;
import us.bojie.appstorebo.common.rx.subscriber.ErrorHandlerSubscriber;
import us.bojie.appstorebo.common.util.ACache;
import us.bojie.appstorebo.common.util.VerificationUtils;
import us.bojie.appstorebo.presenter.contract.LoginContract;

/**
 * Created by bojiejiang on 5/2/17.
 */

public class LoginPresenter extends BasePresenter<LoginContract.ILoginModel, LoginContract.LoginView> {

    @Inject
    public LoginPresenter(LoginContract.ILoginModel model, LoginContract.LoginView view) {
        super(model, view);
    }

    public void login(String phone, String pwd) {
        if (!VerificationUtils.matcherPhoneNum(phone)) {
            mView.checkPhoneError();
        } else {
            mView.checkPhoneSuccess();
        }

        mModel.login(phone, pwd).compose(RxHttpReponseCompat.<LoginBean>compatResult())
                .subscribe(new ErrorHandlerSubscriber<LoginBean>(mContext) {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean value) {
                        mView.loginSuccess(value);
                        saveUser(value);

                        RxBus.get().post(value.getUser());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void saveUser(LoginBean bean) {
        ACache aCache = ACache.get(mContext);

        aCache.put(Constant.TOKEN, bean.getToken());
        aCache.put(Constant.USER, bean.getUser());
    }
}
