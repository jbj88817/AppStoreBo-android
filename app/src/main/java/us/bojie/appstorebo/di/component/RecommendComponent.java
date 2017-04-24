package us.bojie.appstorebo.di.component;

import dagger.Component;
import us.bojie.appstorebo.di.module.RecommendModule;
import us.bojie.appstorebo.di.scope.FragmentScope;
import us.bojie.appstorebo.ui.fragment.RecommendFragment;

/**
 * Created by bojiejiang on 4/23/17.
 */

@FragmentScope
@Component(modules = RecommendModule.class, dependencies = AppComponent.class)
public interface RecommendComponent {
    void inject(RecommendFragment fragment);
}
