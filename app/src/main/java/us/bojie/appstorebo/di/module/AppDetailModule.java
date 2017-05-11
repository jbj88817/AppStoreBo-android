package us.bojie.appstorebo.di.module;

import dagger.Module;
import dagger.Provides;
import us.bojie.appstorebo.presenter.contract.AppInfoContract;

/**
 * Created by bojiejiang on 4/23/17.
 */

@Module(includes = AppModelModule.class)
public class AppDetailModule {

    private AppInfoContract.AppDetailView mView;

    public AppDetailModule(AppInfoContract.AppDetailView view) {
        mView = view;
    }

    @Provides
    public AppInfoContract.AppDetailView provideView() {
        return mView;
    }


}
