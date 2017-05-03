package us.bojie.appstorebo.di.module;

import dagger.Module;
import dagger.Provides;
import us.bojie.appstorebo.data.LoginModel;
import us.bojie.appstorebo.data.http.ApiService;
import us.bojie.appstorebo.presenter.contract.LoginContract;

/**
 * Created by bojiejiang on 4/23/17.
 */

@Module
public class LoginModule {

    private LoginContract.LoginView mView;

    public LoginModule(LoginContract.LoginView view) {
        mView = view;
    }

    @Provides
    public LoginContract.LoginView provideView() {
        return mView;
    }

    @Provides
    public LoginContract.ILoginModel provideModel(ApiService apiService) {
        return new LoginModel(apiService);
    }
}
