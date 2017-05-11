package us.bojie.appstorebo.di.component;

import dagger.Component;
import us.bojie.appstorebo.di.module.AppDetailModule;
import us.bojie.appstorebo.di.scope.FragmentScope;
import us.bojie.appstorebo.ui.fragment.AppDetailFragment;

/**
 * Created by bojiejiang on 4/23/17.
 */

@FragmentScope
@Component(modules = AppDetailModule.class, dependencies = AppComponent.class)
public interface AppDetailComponent {
    void inject(AppDetailFragment fragment);
}
