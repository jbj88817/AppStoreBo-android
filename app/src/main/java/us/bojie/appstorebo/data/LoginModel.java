package us.bojie.appstorebo.data;

import io.reactivex.Observable;
import us.bojie.appstorebo.bean.BaseBean;
import us.bojie.appstorebo.bean.LoginBean;
import us.bojie.appstorebo.bean.requestbean.LoginRequestBean;
import us.bojie.appstorebo.data.http.ApiService;
import us.bojie.appstorebo.presenter.contract.LoginContract;

/**
 * Created by bojiejiang on 5/2/17.
 */

public class LoginModel implements LoginContract.ILoginModel {

    private ApiService mApiService;

    public LoginModel(ApiService apiService) {
        mApiService = apiService;
    }

    @Override
    public Observable<BaseBean<LoginBean>> login(String phone, String pwd) {

        LoginRequestBean param = new LoginRequestBean();
        param.setEmail(phone);
        param.setPassword(pwd);

        return mApiService.login(param);
    }
}
