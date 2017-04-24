package us.bojie.appstorebo.di;

import dagger.Component;
import us.bojie.appstorebo.ui.fragment.RecommendFragment;

/**
 * Created by bojiejiang on 4/23/17.
 */

@Component(modules = RecommendModule.class)
public interface RecommendComponent {
    void inject(RecommendFragment fragment);
}
