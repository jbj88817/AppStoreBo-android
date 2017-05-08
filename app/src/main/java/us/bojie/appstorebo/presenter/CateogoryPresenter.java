package us.bojie.appstorebo.presenter;

import java.util.List;

import javax.inject.Inject;

import us.bojie.appstorebo.bean.Category;
import us.bojie.appstorebo.common.rx.RxHttpReponseCompat;
import us.bojie.appstorebo.common.rx.subscriber.ProgressSubscriber;
import us.bojie.appstorebo.presenter.contract.CategoryContract;

/**
 * Created by bojiejiang on 5/4/17.
 */

public class CateogoryPresenter extends BasePresenter<CategoryContract.ICagegoryModel, CategoryContract.CategoryView> {

    @Inject
    public CateogoryPresenter(CategoryContract.ICagegoryModel model, CategoryContract.CategoryView view) {
        super(model, view);
    }

    public void getAllCategory() {
        mModel.getCategories().compose(RxHttpReponseCompat.<List<Category>>compatResult())
                .subscribe(new ProgressSubscriber<List<Category>>(mContext, mView) {
                    @Override
                    public void onNext(List<Category> value) {
                        mView.showData(value);
                    }
                });
    }
}
