package us.bojie.appstorebo.ui.fragment;

import us.bojie.appstorebo.di.component.AppComponent;
import us.bojie.appstorebo.di.component.DaggerAppInfoComponent;
import us.bojie.appstorebo.di.module.AppInfoModule;
import us.bojie.appstorebo.presenter.AppInfoPresenter;
import us.bojie.appstorebo.ui.adapter.AppInfoAdapter;

public class GamesFragment extends BaseAppInfoFragment {


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
        return AppInfoPresenter.GAMES;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerAppInfoComponent.builder()
                .appComponent(appComponent)
                .appInfoModule(new AppInfoModule(this))
                .build()
                .injectGamesFragment(this);
    }
}
