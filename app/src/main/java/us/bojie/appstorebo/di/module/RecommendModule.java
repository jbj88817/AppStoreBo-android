package us.bojie.appstorebo.di.module;

import android.app.ProgressDialog;

import dagger.Module;
import dagger.Provides;
import us.bojie.appstorebo.data.RecommendModel;
import us.bojie.appstorebo.data.http.ApiService;
import us.bojie.appstorebo.presenter.RecommendPresenter;
import us.bojie.appstorebo.presenter.contract.RecommendContract;
import us.bojie.appstorebo.ui.fragment.RecommendFragment;

/**
 * Created by bojiejiang on 4/23/17.
 */

@Module
public class RecommendModule {

    private RecommendContract.View mView;

    public RecommendModule(RecommendContract.View view) {
        mView = view;
    }

    @Provides
    public RecommendContract.View provideView() {
        return mView;
    }

    @Provides
    public RecommendModel provideModel(ApiService apiService) {
        return new RecommendModel(apiService);
    }

    @Provides
    public RecommendContract.Presenter providePresenter(RecommendContract.View view,
                                                        RecommendModel model) {
        return new RecommendPresenter(view, model);
    }

    @Provides
    public ProgressDialog provideProgressDialog(RecommendContract.View view) {
        return new ProgressDialog(((RecommendFragment) view).getActivity());
    }
}
