package us.bojie.appstorebo.di.module;

import dagger.Module;
import dagger.Provides;
import us.bojie.appstorebo.presenter.contract.AppInfoContract;

/**
 * Created by bojiejiang on 4/23/17.
 */

@Module(includes = AppModelModule.class)
public class AppInfoModule {

    private AppInfoContract.AppInfoView mView;

    public AppInfoModule(AppInfoContract.AppInfoView view) {
        mView = view;
    }

    @Provides
    public AppInfoContract.AppInfoView provideView() {
        return mView;
    }

}
