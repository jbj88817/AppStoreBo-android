package us.bojie.appstorebo.ui.fragment;

import us.bojie.appstorebo.di.component.AppComponent;
import us.bojie.appstorebo.di.component.DaggerAppInfoComponent;
import us.bojie.appstorebo.di.module.AppInfoModule;
import us.bojie.appstorebo.ui.adapter.AppInfoAdapter;

/**
 * Created by bojiejiang on 5/8/17.
 */

public class CategoryAppFragment extends BaseAppInfoFragment {


    private int categoryId;
    private int fragmentType;

    private CategoryAppFragment(int categoryId, int fragmentType) {
        this.categoryId = categoryId;
        this.fragmentType = fragmentType;
    }

    public static CategoryAppFragment newInstance(int categoryId, int fragmentType) {
        return new CategoryAppFragment(categoryId, fragmentType);
    }

    @Override
    AppInfoAdapter buildAdapter() {
        return AppInfoAdapter.builder()
                .showPosition(false)
                .showBrief(true)
                .showCategoryName(false)
                .build();
    }

    @Override
    int type() {
        return 3;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerAppInfoComponent.builder()
                .appComponent(appComponent)
                .appInfoModule(new AppInfoModule(this))
                .build()
                .injectCategoryAppFragment(this);
    }

    @Override
    public void init() {
        mPresenter.requestWithCategory(page, categoryId, fragmentType);
        initRecyclerView();
    }
}
