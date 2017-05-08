package us.bojie.appstorebo.di.component;

import dagger.Component;
import us.bojie.appstorebo.di.module.CategoryModule;
import us.bojie.appstorebo.di.scope.FragmentScope;
import us.bojie.appstorebo.ui.fragment.CategoryFragment;

/**
 * Created by bojiejiang on 4/23/17.
 */

@FragmentScope
@Component(modules = CategoryModule.class, dependencies = AppComponent.class)
public interface CategoryComponent {
    void inject(CategoryFragment fragment);
}
