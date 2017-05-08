package us.bojie.appstorebo.data;

import java.util.List;

import io.reactivex.Observable;
import us.bojie.appstorebo.bean.BaseBean;
import us.bojie.appstorebo.bean.Category;
import us.bojie.appstorebo.data.http.ApiService;
import us.bojie.appstorebo.presenter.contract.CategoryContract;

/**
 * Created by bojiejiang on 5/2/17.
 */

public class CategoryModel implements CategoryContract.ICagegoryModel {

    private ApiService mApiService;

    public CategoryModel(ApiService apiService) {
        mApiService = apiService;
    }

    @Override
    public Observable<BaseBean<List<Category>>> getCategories() {
        return mApiService.getCategories();
    }
}
