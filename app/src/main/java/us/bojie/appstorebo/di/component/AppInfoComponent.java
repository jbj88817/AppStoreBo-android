package us.bojie.appstorebo.di.component;

import dagger.Component;
import us.bojie.appstorebo.di.module.AppInfoModule;
import us.bojie.appstorebo.di.scope.FragmentScope;
import us.bojie.appstorebo.ui.fragment.GamesFragment;
import us.bojie.appstorebo.ui.fragment.TopListFragment;

/**
 * Created by bojiejiang on 4/23/17.
 */

@FragmentScope
@Component(modules = AppInfoModule.class, dependencies = AppComponent.class)
public interface AppInfoComponent {
    void injectTopListFragment(TopListFragment fragment);
    void injectGamesFragment(GamesFragment fragment);
}
