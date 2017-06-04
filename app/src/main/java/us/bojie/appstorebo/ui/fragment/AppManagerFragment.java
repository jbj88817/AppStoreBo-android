package us.bojie.appstorebo.ui.fragment;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import us.bojie.appstorebo.R;
import us.bojie.appstorebo.common.apkparser.AndroidApk;
import us.bojie.appstorebo.presenter.AppManagerPresenter;
import us.bojie.appstorebo.presenter.contract.AppManagerContract;
import zlc.season.rxdownload2.entity.DownloadRecord;

/**
 * Created by bojiejiang on 6/3/17.
 */

public abstract class AppManagerFragment extends ProgressFragment<AppManagerPresenter>
        implements AppManagerContract.AppManagerView {

    @BindView(R.id.recycle_view)
    RecyclerView mRecyclerView;
    
    @Override
    public int setLayout() {
        return R.layout.template_recycler_view;
    }

    @Override
    public void init() {
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);

        mRecyclerView.setAdapter(setupAdapter());

    }

    protected abstract RecyclerView.Adapter setupAdapter();

    @Override
    public void showApps(List<AndroidApk> apps) {

    }

    @Override
    public void showDownloading(List<DownloadRecord> records) {

    }
   
}
