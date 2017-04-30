package us.bojie.appstorebo.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import us.bojie.appstorebo.AppApplication;
import us.bojie.appstorebo.di.component.AppComponent;
import us.bojie.appstorebo.presenter.BasePresenter;
import us.bojie.appstorebo.ui.BaseView;

/**
 * Created by bojiejiang on 4/23/17.
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements BaseView {

    private Unbinder mUnbinder;
    private AppApplication mApplication;
    private View mRootView;

    @Inject
    T mPresenter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(setLayout(), container, false);
        mUnbinder = ButterKnife.bind(this, mRootView);
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mApplication = (AppApplication) getActivity().getApplication();
        setupActivityComponent(mApplication.getAppComponent());
        init();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != mUnbinder.EMPTY) {
            mUnbinder.unbind();
        }
    }

    public abstract int setLayout();

    public abstract void setupActivityComponent(AppComponent appComponent);

    public abstract void init();

    @Override
    public void showLoading() {
    }

    @Override
    public void dismissLoading() {
    }

    @Override
    public void showError(String msg) {
    }
}
