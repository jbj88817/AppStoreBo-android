package us.bojie.appstorebo.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import us.bojie.appstorebo.R;
import us.bojie.appstorebo.common.util.DensityUtil;
import us.bojie.appstorebo.di.component.AppComponent;

public class AppDetailActivity extends BaseActivity {


    @BindView(R.id.view_content)
    FrameLayout mViewContent;

    @Override
    public int setLayout() {
        return R.layout.activity_app_detail;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void init() {
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

        extension();
    }

    private void extension() {

        int height = DensityUtil.getScreenH(this);

        ObjectAnimator animator = ObjectAnimator.ofFloat(mViewContent, "scaleY", 1, (float) height);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
