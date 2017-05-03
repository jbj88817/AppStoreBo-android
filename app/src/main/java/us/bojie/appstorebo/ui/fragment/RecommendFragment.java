package us.bojie.appstorebo.ui.fragment;

import android.app.ProgressDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import us.bojie.appstorebo.R;
import us.bojie.appstorebo.bean.IndexBean;
import us.bojie.appstorebo.di.component.AppComponent;
import us.bojie.appstorebo.di.component.DaggerRecommendComponent;
import us.bojie.appstorebo.di.module.RecommendModule;
import us.bojie.appstorebo.presenter.RecommendPresenter;
import us.bojie.appstorebo.presenter.contract.AppInfoContract;
import us.bojie.appstorebo.ui.adapter.IndexMutilAdapter;


public class RecommendFragment extends ProgressFragment<RecommendPresenter> implements AppInfoContract.View {

    @BindView(R.id.recycle_view)
    RecyclerView mRecycleView;

    private IndexMutilAdapter mAdapter;

    @Inject
    ProgressDialog mProgressDialog;


    @Override
    public int setLayout() {
        return R.layout.template_recycler_view;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerRecommendComponent.builder()
                .appComponent(appComponent)
                .recommendModule(new RecommendModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void init() {
        initRecycleView();
        mPresenter.requestData();
    }


    private void initRecycleView() {
        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycleView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void showLoading() {
        mProgressDialog.show();
    }

    @Override
    public void dismissLoading() {
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void onEmptyViewClick() {
        mPresenter.requestData();
    }

    @Override
    public void showResult(IndexBean indexBean) {
        mAdapter = new IndexMutilAdapter(getActivity());
        mAdapter.setData(indexBean);
        mRecycleView.setAdapter(mAdapter);
    }

    @Override
    public void onRequestPermissionSuccess() {
        mPresenter.requestData();
    }

    @Override
    public void onRequestPermissionError() {
        Toast.makeText(getActivity(), "Permission Denied!", Toast.LENGTH_SHORT).show();
    }


}
