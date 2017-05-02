package us.bojie.appstorebo.ui.fragment;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import butterknife.BindView;
import us.bojie.appstorebo.R;
import us.bojie.appstorebo.bean.AppInfo;
import us.bojie.appstorebo.bean.PageBean;
import us.bojie.appstorebo.di.component.AppComponent;
import us.bojie.appstorebo.di.component.DaggerTopListComponent;
import us.bojie.appstorebo.di.module.TopListModule;
import us.bojie.appstorebo.presenter.TopListPresenter;
import us.bojie.appstorebo.presenter.contract.AppInfoContract;
import us.bojie.appstorebo.ui.adapter.AppInfoAdapter;

public class TopListFragment extends ProgressFragment<TopListPresenter>
        implements AppInfoContract.TopListView, BaseQuickAdapter.RequestLoadMoreListener{


    @BindView(R.id.recycle_view)
    RecyclerView mRecyclerView;

    AppInfoAdapter mAdapter;
    int page = 0;

    @Override
    public int setLayout() {
        return R.layout.template_recycler_view;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerTopListComponent.builder()
                .appComponent(appComponent)
                .topListModule(new TopListModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void init() {
        mPresenter.getTopListApps(page);
        initRecyclerView();
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);

        mRecyclerView.addItemDecoration(itemDecoration);

        mAdapter = AppInfoAdapter.builder()
                .showPosition(true)
                .showBrief(false)
                .showCategoryName(true)
                .build();
        mAdapter.setOnLoadMoreListener(this, mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void showResult(PageBean<AppInfo> appInfoPageBean) {
        mAdapter.addData(appInfoPageBean.getDatas());
        if (appInfoPageBean.isHasMore()) {
            page++;
        }
        mAdapter.setEnableLoadMore(appInfoPageBean.isHasMore());
    }

    @Override
    public void onLoadMoreCompleted() {
        mAdapter.loadMoreComplete();
    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.getTopListApps(page);
    }
}
