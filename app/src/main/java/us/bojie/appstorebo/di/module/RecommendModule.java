package us.bojie.appstorebo.di.module;

import android.app.ProgressDialog;

import dagger.Module;
import dagger.Provides;
import us.bojie.appstorebo.data.AppInfoModel;
import us.bojie.appstorebo.data.http.ApiService;
import us.bojie.appstorebo.presenter.contract.AppInfoContract;
import us.bojie.appstorebo.ui.fragment.RecommendFragment;

/**
 * Created by bojiejiang on 4/23/17.
 */

@Module
public class RecommendModule {

    private AppInfoContract.View mView;

    public RecommendModule(AppInfoContract.View view) {
        mView = view;
    }

    @Provides
    public AppInfoContract.View provideView() {
        return mView;
    }

    @Provides
    public AppInfoModel provideModel(ApiService apiService) {
        return new AppInfoModel(apiService);
    }

    @Provides
    public ProgressDialog provideProgressDialog(AppInfoContract.View view) {
        return new ProgressDialog(((RecommendFragment) view).getActivity());
    }
}
