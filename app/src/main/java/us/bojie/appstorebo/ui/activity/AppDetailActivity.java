package us.bojie.appstorebo.ui.activity;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import us.bojie.appstorebo.R;
import us.bojie.appstorebo.di.component.AppComponent;

public class AppDetailActivity extends BaseActivity {


    @BindView(R.id.imgView)
    ImageView mImgView;

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
            mImgView.setImageBitmap(bitmap);
        }
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
}
