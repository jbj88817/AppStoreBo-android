package us.bojie.appstorebo.di.component;

import javax.inject.Singleton;

import dagger.Component;
import us.bojie.appstorebo.data.http.ApiService;
import us.bojie.appstorebo.di.module.AppModule;
import us.bojie.appstorebo.di.module.HttpModule;

/**
 * Created by bojiejiang on 4/23/17.
 */

@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {

    public ApiService getApiService();

}
