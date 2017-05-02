package us.bojie.appstorebo.di.component;

import dagger.Component;
import us.bojie.appstorebo.di.module.TopListModule;
import us.bojie.appstorebo.di.scope.FragmentScope;
import us.bojie.appstorebo.ui.fragment.TopListFragment;

/**
 * Created by bojiejiang on 4/23/17.
 */

@FragmentScope
@Component(modules = TopListModule.class, dependencies = AppComponent.class)
public interface TopListComponent {
    void inject(TopListFragment fragment);
}
