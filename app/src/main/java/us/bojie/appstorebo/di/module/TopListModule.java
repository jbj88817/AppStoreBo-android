package us.bojie.appstorebo.di.module;

import dagger.Module;
import dagger.Provides;
import us.bojie.appstorebo.data.AppInfoModel;
import us.bojie.appstorebo.data.http.ApiService;
import us.bojie.appstorebo.presenter.contract.AppInfoContract;

/**
 * Created by bojiejiang on 4/23/17.
 */

@Module
public class TopListModule {

    private AppInfoContract.TopListView mView;

    public TopListModule(AppInfoContract.TopListView view) {
        mView = view;
    }

    @Provides
    public AppInfoContract.TopListView provideView() {
        return mView;
    }

    @Provides
    public AppInfoModel provideModel(ApiService apiService) {
        return new AppInfoModel(apiService);
    }

}
