package us.bojie.appstorebo.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;

import butterknife.BindView;
import us.bojie.appstorebo.R;
import us.bojie.appstorebo.bean.AppInfo;
import us.bojie.appstorebo.common.imageloader.ImageLoader;
import us.bojie.appstorebo.common.util.DensityUtil;
import us.bojie.appstorebo.data.http.ApiService;
import us.bojie.appstorebo.di.component.AppComponent;
import us.bojie.appstorebo.presenter.AppDetailPresenter;
import us.bojie.appstorebo.ui.fragment.AppDetailFragment;
import us.bojie.appstorebo.ui.fragment.BaseAppInfoFragment;

public class AppDetailActivity extends BaseActivity<AppDetailPresenter> {


    @BindView(R.id.view_content)
    FrameLayout mViewContent;
    @BindView(R.id.img_icon)
    ImageView mImgIcon;
    @BindView(R.id.txt_name)
    TextView mTxtName;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private AppInfo mAppInfo;

    @Override
    public int setLayout() {
        return R.layout.activity_app_detail;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
    }

    @Override
    public void init() {

        mAppInfo = (AppInfo) getIntent().getSerializableExtra(BaseAppInfoFragment.APPINFO);

        mToolbar.setNavigationIcon(
                new IconicsDrawable(this)
                        .icon(Ionicons.Icon.ion_ios_arrow_back)
                        .sizeDp(16)
                        .color(getResources().getColor(R.color.md_white_1000))
        );

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AppDetailActivity.this, MainActivity.class));
            }
        });

        ImageLoader.load(ApiService.BASE_IMG_URL + mAppInfo.getIcon(), mImgIcon);
        mTxtName.setText(mAppInfo.getDisplayName());

//        popupView();

//        extension();

        initFragment();
    }

    private void popupView() {
        View view = mApplication.getView();
        Bitmap bitmap = getViewImageCache(view);

        if (bitmap != null) {
            mViewContent.setBackground(new BitmapDrawable(getResources(), bitmap));
        }

        int[] location = new int[2];
        view.getLocationOnScreen(location);

        int left = location[0];
        int top = location[1];

        ViewGroup.MarginLayoutParams marginLayoutParams =
                new ViewGroup.MarginLayoutParams(mViewContent.getLayoutParams());
        marginLayoutParams.topMargin = top - DensityUtil.getStatusBarH(this);
        marginLayoutParams.leftMargin = left;
        marginLayoutParams.width = view.getWidth();
        marginLayoutParams.height = view.getHeight();

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(marginLayoutParams);

        mViewContent.setLayoutParams(params);
    }

    private void extension() {

        int height = DensityUtil.getScreenH(this);

        ObjectAnimator animator = ObjectAnimator.ofFloat(mViewContent, "scaleY", 1, (float) height);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                initFragment();
            }

            @Override
            public void onAnimationStart(Animator animation) {
                mViewContent.setBackgroundColor(getResources().getColor(R.color.white));
            }
        });
        animator.setStartDelay(800);
        animator.setDuration(10000);
        animator.start();
    }

    private Bitmap getViewImageCache(View view) {
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();

        Bitmap bitmap = view.getDrawingCache();

        if (bitmap == null) {
            return null;
        }

        bitmap = Bitmap.createBitmap(bitmap);
        view.destroyDrawingCache();
        return bitmap;
    }

    private void initFragment() {
        AppDetailFragment fragment = new AppDetailFragment(mAppInfo.getId());
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.view_content, fragment);
        transaction.commitAllowingStateLoss();
    }

}
