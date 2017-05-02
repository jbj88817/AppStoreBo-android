package us.bojie.appstorebo.ui.fragment;

import android.Manifest;
import android.app.ProgressDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.tbruyelle.rxpermissions2.RxPermissions;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.functions.Consumer;
import us.bojie.appstorebo.R;
import us.bojie.appstorebo.bean.IndexBean;
import us.bojie.appstorebo.di.component.AppComponent;
import us.bojie.appstorebo.di.component.DaggerRecommendComponent;
import us.bojie.appstorebo.di.module.RecommendModule;
import us.bojie.appstorebo.presenter.RecommendPresenter;
import us.bojie.appstorebo.presenter.contract.RecommendContract;
import us.bojie.appstorebo.ui.adapter.IndexMutilAdapter;


public class RecommendFragment extends ProgressFragment<RecommendPresenter> implements RecommendContract.View {

    @BindView(R.id.recycle_view)
    RecyclerView mRecycleView;

    private IndexMutilAdapter mAdapter;

    @Inject
    ProgressDialog mProgressDialog;


    @Override
    public int setLayout() {
        return R.layout.fragment_recommend;
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
        getPermission();
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

    public void getPermission() {
        RxPermissions rxPermissions = new RxPermissions(getActivity());
        rxPermissions.request(Manifest.permission.READ_PHONE_STATE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            Toast.makeText(getActivity(), "got permission", Toast.LENGTH_SHORT).show();
                            mPresenter.requestData();
                        }
                    }
                });

    }
}
