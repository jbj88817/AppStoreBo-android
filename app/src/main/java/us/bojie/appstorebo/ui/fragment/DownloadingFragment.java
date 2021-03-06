package us.bojie.appstorebo.ui.fragment;


import android.support.v7.widget.RecyclerView;

import java.util.List;

import us.bojie.appstorebo.di.component.AppComponent;
import us.bojie.appstorebo.di.component.DaggerAppManagerComponent;
import us.bojie.appstorebo.di.module.AppManagerModule;
import us.bojie.appstorebo.ui.adapter.DownloadingAdapter;
import zlc.season.rxdownload2.entity.DownloadRecord;

public class DownloadingFragment extends AppManagerFragment {


    private DownloadingAdapter mAdapter;

    @Override
    public void init() {
        super.init();
        mPresenter.getDownloadingApps();
    }

    @Override
    protected RecyclerView.Adapter setupAdapter() {
        mAdapter = new DownloadingAdapter(mPresenter.getRxDownload());
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
    public void showDownloading(List<DownloadRecord> records) {
        mAdapter.addData(records);
    }


}
