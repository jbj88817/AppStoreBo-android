package us.bojie.appstorebo.di.module;

import android.app.Application;

import dagger.Module;
import dagger.Provides;
import us.bojie.appstorebo.data.AppManagerModel;
import us.bojie.appstorebo.presenter.contract.AppManagerContract;
import zlc.season.rxdownload2.RxDownload;

/**
 * Created by bojiejiang on 6/3/17.
 */

@Module()
public class AppManagerModule {

    private AppManagerContract.AppManagerView mView;

    public AppManagerModule(AppManagerContract.AppManagerView view) {
        mView = view;
    }

    @Provides
    public AppManagerContract.AppManagerView provideView() {
        return mView;
    }

    @Provides
    public AppManagerContract.IAppMangerModel provideModel(Application application, RxDownload rxDownload) {
        return new AppManagerModel(application, rxDownload);
    }
}