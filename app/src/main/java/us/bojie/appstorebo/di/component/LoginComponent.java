package us.bojie.appstorebo.di.component;

import dagger.Component;
import us.bojie.appstorebo.di.module.LoginModule;
import us.bojie.appstorebo.di.scope.FragmentScope;
import us.bojie.appstorebo.ui.activity.LoginActivity;

/**
 * Created by bojiejiang on 4/23/17.
 */

@FragmentScope
@Component(modules = LoginModule.class, dependencies = AppComponent.class)
public interface LoginComponent {
    void inject(LoginActivity activity);
}
