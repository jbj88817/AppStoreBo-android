package us.bojie.appstorebo.ui.fragment;

import us.bojie.appstorebo.di.component.AppComponent;
import us.bojie.appstorebo.di.component.DaggerAppInfoComponent;
import us.bojie.appstorebo.di.module.AppInfoModule;
import us.bojie.appstorebo.presenter.AppInfoPresenter;
import us.bojie.appstorebo.ui.adapter.AppInfoAdapter;

public class TopListFragment extends BaseAppInfoFragment {


    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerAppInfoComponent.builder()
                .appComponent(appComponent)
                .appInfoModule(new AppInfoModule(this))
                .build()
                .injectTopListFragment(this);
    }

    @Override
    AppInfoAdapter buildAdapter() {
        return AppInfoAdapter.builder()
                .showPosition(true)
                .showBrief(false)
                .showCategoryName(true)
                .build();
    }

    @Override
    int type() {
        return AppInfoPresenter.TOP_LIST;
    }

}
