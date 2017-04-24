package us.bojie.appstorebo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;

import com.mikepenz.iconics.context.IconicsLayoutInflater;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import us.bojie.appstorebo.AppApplication;
import us.bojie.appstorebo.di.component.AppComponent;
import us.bojie.appstorebo.presenter.BasePresenter;

/**
 * Created by bojiejiang on 4/23/17.
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {

    private Unbinder mUnbinder;
    private AppApplication mApplication;

    @Inject
    T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        LayoutInflaterCompat.setFactory(getLayoutInflater(), new IconicsLayoutInflater(getDelegate()));
        super.onCreate(savedInstanceState);

        setContentView(setLayout());
        mUnbinder = ButterKnife.bind(this);
        mApplication = (AppApplication) getApplication();
        setupActivityComponent(mApplication.getAppComponent());
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != mUnbinder.EMPTY) {
            mUnbinder.unbind();
        }
    }

    protected void startActivity(Class clazz) {
        this.startActivity(new Intent(this, clazz));
    }


    public abstract int setLayout();

    public abstract void setupActivityComponent(AppComponent appComponent);

    public abstract void init();
}
