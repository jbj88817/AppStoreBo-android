package us.bojie.appstorebo.ui.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import us.bojie.appstorebo.AppApplication;
import us.bojie.appstorebo.R;
import us.bojie.appstorebo.bean.AppInfo;
import us.bojie.appstorebo.di.component.AppComponent;
import us.bojie.appstorebo.di.component.DaggerRecommendComponent;
import us.bojie.appstorebo.di.module.RecommendModule;
import us.bojie.appstorebo.presenter.contract.RecommendContract;
import us.bojie.appstorebo.ui.adapter.RecommendAppAdapter;


public class RecommendFragment extends Fragment implements RecommendContract.View {

    @BindView(R.id.recycle_view)
    RecyclerView mRecycleView;
    Unbinder unbinder;

    private RecommendAppAdapter mAdapter;

    @Inject
    ProgressDialog mProgressDialog;

    @Inject
    RecommendContract.Presenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommend, container, false);
        unbinder = ButterKnife.bind(this, view);

        AppComponent appComponent = ((AppApplication) getActivity().getApplication()).getAppComponent();

        DaggerRecommendComponent.builder()
                .appComponent(appComponent)
                .recommendModule(new RecommendModule(this))
                .build()
                .inject(this);

        initData();
        return view;
    }


    private void initData() {
        mPresenter.requestData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    private void initRecycleView(List<AppInfo> data) {
        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycleView.setItemAnimator(new DefaultItemAnimator());

        // Set line between each item
        mRecycleView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        mAdapter = new RecommendAppAdapter(getActivity(), data);
        mRecycleView.setAdapter(mAdapter);

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
    public void showResult(List<AppInfo> data) {
        initRecycleView(data);
    }

    @Override
    public void showNoData() {
        Toast.makeText(getActivity(), "No data", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(getActivity(), "Connection error: " + msg, Toast.LENGTH_SHORT).show();
    }
}
