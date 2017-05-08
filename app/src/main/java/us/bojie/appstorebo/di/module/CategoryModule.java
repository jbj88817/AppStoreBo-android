package us.bojie.appstorebo.di.module;

import dagger.Module;
import dagger.Provides;
import us.bojie.appstorebo.data.CategoryModel;
import us.bojie.appstorebo.data.http.ApiService;
import us.bojie.appstorebo.presenter.contract.CategoryContract;

/**
 * Created by bojiejiang on 4/23/17.
 */

@Module
public class CategoryModule {

    private CategoryContract.CategoryView mView;

    public CategoryModule(CategoryContract.CategoryView view) {
        mView = view;
    }

    @Provides
    public CategoryContract.CategoryView provideView() {
        return mView;
    }

    @Provides
    public CategoryContract.ICagegoryModel provideModel(ApiService apiService) {
        return new CategoryModel(apiService);
    }
}
