package us.bojie.appstorebo.ui.activity;

import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.Toast;

import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import us.bojie.appstorebo.R;
import us.bojie.appstorebo.bean.LoginBean;
import us.bojie.appstorebo.di.component.AppComponent;
import us.bojie.appstorebo.di.component.DaggerLoginComponent;
import us.bojie.appstorebo.di.module.LoginModule;
import us.bojie.appstorebo.presenter.LoginPresenter;
import us.bojie.appstorebo.presenter.contract.LoginContract;
import us.bojie.appstorebo.ui.widget.LoadingButton;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.LoginView {


    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.txt_mobi)
    EditText mTxtMobi;
    @BindView(R.id.view_mobi_wrapper)
    TextInputLayout mViewMobiWrapper;
    @BindView(R.id.txt_password)
    EditText mTxtPassword;
    @BindView(R.id.view_password_wrapper)
    TextInputLayout mViewPasswordWrapper;
    @BindView(R.id.btn_login)
    LoadingButton mBtnLogin;

    @Override
    public int setLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerLoginComponent.builder()
                .appComponent(appComponent)
                .loginModule(new LoginModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void init() {
        initView();
    }

    private void initView() {

        mToolBar.setNavigationIcon(
                new IconicsDrawable(this)
                .icon(Ionicons.Icon.ion_ios_arrow_back)
                .sizeDp(16)
                .color(getResources().getColor(R.color.md_white_1000))
        );

        Observable<CharSequence> mobileObservable = RxTextView.textChanges(mTxtMobi);
        Observable<CharSequence> passwordObservable = RxTextView.textChanges(mTxtPassword);

        Observable.combineLatest(mobileObservable, passwordObservable, new BiFunction<CharSequence, CharSequence, Boolean>() {
            @Override
            public Boolean apply(CharSequence mobi, CharSequence pwd) throws Exception {
                return isPhoneValid(mobi.toString()) && isPasswordValid(pwd.toString());
            }
        }).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                RxView.enabled(mBtnLogin).accept(aBoolean);
            }
        });


        RxView.clicks(mBtnLogin).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                mPresenter.login(mTxtMobi.getText().toString().trim(),
                        mTxtPassword.getText().toString().trim());
            }
        });
    }

    private boolean isPhoneValid(String phone) {
        return phone.length() == 11;
    }

    private boolean isPasswordValid(String password) {
        return password.length() >= 6;
    }

    @Override
    public void showLoading() {
        mBtnLogin.showLoading();
    }

    @Override
    public void dismissLoading() {
        mBtnLogin.showButtonText();
    }

    @Override
    public void showError(String msg) {
        mBtnLogin.showButtonText();
    }

    @Override
    public void checkPhoneError() {
        mViewMobiWrapper.setError("Phone format is wrong");
    }

    @Override
    public void checkPhoneSuccess() {
        mViewMobiWrapper.setError("");
    }

    @Override
    public void loginSuccess(LoginBean bean) {
        Toast.makeText(this, "Login success!", Toast.LENGTH_SHORT).show();
        finish();
    }
}
