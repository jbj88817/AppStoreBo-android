package us.bojie.appstorebo.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import us.bojie.appstorebo.R;
import us.bojie.appstorebo.bean.AppInfo;
import us.bojie.appstorebo.common.imageloader.ImageLoader;
import us.bojie.appstorebo.common.util.DateUtils;
import us.bojie.appstorebo.data.http.ApiService;
import us.bojie.appstorebo.di.component.AppComponent;
import us.bojie.appstorebo.di.component.DaggerAppDetailComponent;
import us.bojie.appstorebo.di.module.AppDetailModule;
import us.bojie.appstorebo.di.module.AppModelModule;
import us.bojie.appstorebo.presenter.AppDetailPresenter;
import us.bojie.appstorebo.presenter.contract.AppInfoContract;
import us.bojie.appstorebo.ui.adapter.AppInfoAdapter;

/**
 * Created by bojiejiang on 5/10/17.
 */

public class AppDetailFragment extends ProgressFragment<AppDetailPresenter> implements AppInfoContract.AppDetailView {

    @BindView(R.id.view_gallery)
    LinearLayout mViewGallery;
    @BindView(R.id.expandable_text)
    TextView mExpandableText;
    @BindView(R.id.expand_collapse)
    ImageButton mExpandCollapse;
    @BindView(R.id.view_introduction)
    ExpandableTextView mViewIntroduction;
    @BindView(R.id.txt_update_time)
    TextView mTxtUpdateTime;
    @BindView(R.id.txt_version)
    TextView mTxtVersion;
    @BindView(R.id.txt_apk_size)
    TextView mTxtApkSize;
    @BindView(R.id.txt_publisher)
    TextView mTxtPublisher;
    @BindView(R.id.txt_publisher2)
    TextView mTxtPublisher2;
    @BindView(R.id.recycler_view_same_dev)
    RecyclerView mRecyclerViewSameDev;
    @BindView(R.id.recycler_view_relate)
    RecyclerView mRecyclerViewRelate;

    private int mAppId;
    private LayoutInflater mInflater;
    private AppInfoAdapter mAdapter;

    public AppDetailFragment(int appId) {
        mAppId = appId;
    }

    @Override
    public int setLayout() {
        return R.layout.fragment_app_detail;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerAppDetailComponent.builder()
                .appComponent(appComponent)
                .appDetailModule(new AppDetailModule(this))
                .appModelModule(new AppModelModule())
                .build().inject(this);

    }

    @Override
    public void init() {
        mInflater = LayoutInflater.from(getActivity());
        mPresenter.getAppDetail(mAppId);
    }

    @Override
    public void showAppDetail(AppInfo appInfo) {
        showScreenshot(appInfo.getScreenshot());
        mViewIntroduction.setText(appInfo.getIntroduction());

        mTxtUpdateTime.setText(DateUtils.formatDate(appInfo.getUpdateTime()));
        mTxtApkSize.setText((appInfo.getApkSize() / 1014 / 1024) + " Mb");
        mTxtVersion.setText(appInfo.getVersionName());
        mTxtPublisher.setText(appInfo.getPublisherName());
        mTxtPublisher2.setText(appInfo.getPublisherName());

        mAdapter = AppInfoAdapter.builder()
                .setLayoutId(R.layout.template_appinfo2)
                .build();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerViewSameDev.setLayoutManager(layoutManager);
        mAdapter.addData(appInfo.getSameDevAppInfoList());
        mRecyclerViewSameDev.setAdapter(mAdapter);

        //-----------------------------------------

        mAdapter = AppInfoAdapter.builder()
                .setLayoutId(R.layout.template_appinfo2)
                .build();
        mRecyclerViewRelate.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        mAdapter.addData(appInfo.getRelateAppInfoList());
        mRecyclerViewRelate.setAdapter(mAdapter);
    }

    private void showScreenshot(String screentShot) {
        List<String> urls = Arrays.asList(screentShot.split(","));

        for (String url : urls) {
            ImageView imageView = (ImageView) mInflater.inflate(R.layout.template_imageview, mViewGallery, false);
            ImageLoader.load(ApiService.BASE_IMG_URL + url, imageView);
            mViewGallery.addView(imageView);
        }
    }
}
