package us.bojie.appstorebo.presenter.contract;

import java.util.List;

import io.reactivex.Observable;
import us.bojie.appstorebo.bean.BaseBean;
import us.bojie.appstorebo.bean.Category;
import us.bojie.appstorebo.ui.BaseView;

/**
 * Created by bojiejiang on 5/2/17.
 */

public interface CategoryContract {

    interface ICagegoryModel{
        Observable<BaseBean<List<Category>>> getCategories();
    }

    interface CategoryView extends BaseView {
        void showData(List<Category> categories);
    }
}
