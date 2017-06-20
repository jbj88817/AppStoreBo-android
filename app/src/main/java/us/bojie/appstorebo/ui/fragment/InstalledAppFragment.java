package us.bojie.appstorebo.ui.fragment;


import android.support.v7.widget.RecyclerView;

import java.util.List;

import us.bojie.appstorebo.common.apkparser.AndroidApk;
import us.bojie.appstorebo.di.component.AppComponent;
import us.bojie.appstorebo.di.component.DaggerAppManagerComponent;
import us.bojie.appstorebo.di.module.AppManagerModule;
import us.bojie.appstorebo.ui.adapter.AndroidApkAdapter;

public class InstalledAppFragment extends AppManagerFragment {

    AndroidApkAdapter mAdapter;

    @Override
    protected RecyclerView.Adapter setupAdapter() {
        mAdapter = new AndroidApkAdapter(AndroidApkAdapter.FLAG_APP);
        return mAdapter;
    }


    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerAppManagerComponent.builder()
                .appComponent(appComponent)
                .appManagerModule(new AppManagerModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void showApps(List<AndroidApk> apps) {
       mAdapter.addData(apps);
    }
}
