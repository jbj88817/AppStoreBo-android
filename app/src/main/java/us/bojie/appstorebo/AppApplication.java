package us.bojie.appstorebo;

import android.app.Application;
import android.content.Context;

import us.bojie.appstorebo.di.component.AppComponent;
import us.bojie.appstorebo.di.component.DaggerAppComponent;
import us.bojie.appstorebo.di.module.AppModule;
import us.bojie.appstorebo.di.module.HttpModule;

/**
 * Created by bojiejiang on 4/23/17.
 */

public class AppApplication extends Application {

    private AppComponent mAppComponent;

    public static AppApplication getApplication(Context context) {
        return (AppApplication) context.getApplicationContext();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .httpModule(new HttpModule())
                .build();
    }
}
