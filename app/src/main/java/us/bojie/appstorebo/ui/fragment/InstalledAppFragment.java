package us.bojie.appstorebo.ui.fragment;


import android.support.v7.widget.RecyclerView;

import us.bojie.appstorebo.di.component.AppComponent;
import us.bojie.appstorebo.di.component.DaggerAppManagerComponent;
import us.bojie.appstorebo.di.module.AppManagerModule;

public class InstalledAppFragment extends AppManagerFragment {

    @Override
    protected RecyclerView.Adapter setupAdapter() {
        return null;
    }


    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerAppManagerComponent.builder()
                .appComponent(appComponent)
                .appManagerModule(new AppManagerModule(this))
                .build()
                .inject(this);
    }

}
