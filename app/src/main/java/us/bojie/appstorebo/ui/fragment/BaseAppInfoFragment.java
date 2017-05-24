package us.bojie.appstorebo.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;

import javax.inject.Inject;

import butterknife.BindView;
import us.bojie.appstorebo.R;
import us.bojie.appstorebo.bean.AppInfo;
import us.bojie.appstorebo.bean.PageBean;
import us.bojie.appstorebo.presenter.AppInfoPresenter;
import us.bojie.appstorebo.presenter.contract.AppInfoContract;
import us.bojie.appstorebo.ui.activity.AppDetailActivity;
import us.bojie.appstorebo.ui.adapter.AppInfoAdapter;
import zlc.season.rxdownload2.RxDownload;

/**
 * Created by bojiejiang on 5/1/17.
 */

public abstract class BaseAppInfoFragment extends ProgressFragment<AppInfoPresenter>
        implements AppInfoContract.AppInfoView, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.recycle_view)
    RecyclerView mRecyclerView;

    @Inject
    RxDownload mRxDownload;

    protected AppInfoAdapter mAdapter;
    int page = 0;

    public static final String APPINFO = "appinfo";

    @Override
    public int setLayout() {
        return R.layout.template_recycler_view;
    }


    @Override
    public void init() {
        mPresenter.requestData(type(), page);
        initRecyclerView();
    }

    protected void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);

        mRecyclerView.addItemDecoration(itemDecoration);

        mAdapter = buildAdapter();
        mAdapter.setOnLoadMoreListener(this, mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {

                AppInfo appInfo = mAdapter.getItem(position);
                mApplication.setView(view);
                Intent intent = new Intent(getActivity(), AppDetailActivity.class);
                intent.putExtra(APPINFO, appInfo);
                startActivity(intent);
            }
        });
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
        mPresenter.requestData(type(),page);
    }

    abstract AppInfoAdapter buildAdapter();

    abstract int type();
}
