package us.bojie.appstorebo.di.module;

import dagger.Module;
import dagger.Provides;
import us.bojie.appstorebo.data.AppInfoModel;
import us.bojie.appstorebo.data.http.ApiService;

/**
 * Created by bojiejiang on 4/23/17.
 */

@Module
public class AppModelModule {

    @Provides
    public AppInfoModel provideModel(ApiService apiService) {
        return new AppInfoModel(apiService);
    }

}
