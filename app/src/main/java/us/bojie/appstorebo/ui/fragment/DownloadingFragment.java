package us.bojie.appstorebo.ui.fragment;


import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import us.bojie.appstorebo.R;
import us.bojie.appstorebo.di.component.AppComponent;
import us.bojie.appstorebo.di.component.DaggerAppManagerComponent;
import us.bojie.appstorebo.di.module.AppManagerModule;
import us.bojie.appstorebo.presenter.AppManagerPresenter;
import us.bojie.appstorebo.presenter.contract.AppManagerContract;
import us.bojie.appstorebo.ui.adapter.DownloadingAdapter;
import zlc.season.rxdownload2.entity.DownloadRecord;

public class DownloadingFragment extends ProgressFragment<AppManagerPresenter> implements AppManagerContract.AppManagerView {


    @BindView(R.id.recycle_view)
    RecyclerView mRecyclerView;

    private DownloadingAdapter mAdapter;

    @Override
    public int setLayout() {
        return R.layout.template_recycler_view;
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
    public void init() {
        setupRecyclerView();
        mPresenter.getDownloadingApps();
    }

    private void setupRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);

        mAdapter = new DownloadingAdapter(mPresenter.getRxDownload());
//        mAdapter.setOnLoadMoreListener(this, mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void showDownloading(List<DownloadRecord> records) {
        mAdapter.addData(records);
    }
}
