package us.bojie.appstorebo.presenter.contract;

import io.reactivex.Observable;
import us.bojie.appstorebo.bean.BaseBean;
import us.bojie.appstorebo.bean.LoginBean;
import us.bojie.appstorebo.ui.BaseView;

/**
 * Created by bojiejiang on 5/2/17.
 */

public interface LoginContract {

    interface ILoginModel{
        Observable<BaseBean<LoginBean>> login(String phone, String pwd);
    }

    interface LoginView extends BaseView {
        void checkPhoneError();
        void checkPhoneSuccess();
        void loginSuccess(LoginBean bean);
//        void loginFailed(String msg);

    }
}
