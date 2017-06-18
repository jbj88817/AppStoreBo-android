package us.bojie.appstorebo.di.component;

import dagger.Component;
import us.bojie.appstorebo.di.module.AppManagerModule;
import us.bojie.appstorebo.di.scope.FragmentScope;
import us.bojie.appstorebo.ui.fragment.DownloadedFragment;
import us.bojie.appstorebo.ui.fragment.DownloadingFragment;
import us.bojie.appstorebo.ui.fragment.InstalledAppFragment;

/**
 * Created by bojiejiang on 4/23/17.
 */

@FragmentScope
@Component(modules = AppManagerModule.class, dependencies = AppComponent.class)
public interface AppManagerComponent {
    void inject(DownloadingFragment fragment);
    void inject(DownloadedFragment fragment);
    void inject(InstalledAppFragment fragment);
}
