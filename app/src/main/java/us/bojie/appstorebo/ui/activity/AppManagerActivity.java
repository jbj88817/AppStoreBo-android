package us.bojie.appstorebo.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import us.bojie.appstorebo.R;
import us.bojie.appstorebo.di.component.AppComponent;
import us.bojie.appstorebo.ui.adapter.ViewPagerAdapter;
import us.bojie.appstorebo.ui.bean.FragmentInfo;
import us.bojie.appstorebo.ui.fragment.DownloadedFragment;
import us.bojie.appstorebo.ui.fragment.DownloadingFragment;

public class AppManagerActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.tabs)
    TabLayout mTabLayout;

    @BindView(R.id.viewpager)
    ViewPager mViewPager;


    private void initToolbar() {


        mToolbar.setNavigationIcon(
                new IconicsDrawable(this)
                        .icon(Ionicons.Icon.ion_ios_arrow_back)
                        .sizeDp(16)
                        .color(getResources().getColor(R.color.md_white_1000)
                        )
        );

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mToolbar.setTitle(R.string.download_manager);
    }


    @Override
    public int setLayout() {
        return R.layout.activity_download_manager;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {

    }


    @Override
    public void init() {
        initToolbar();
        initTablayout();
    }


    private void initTablayout() {

        PagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), initFragments());
        mViewPager.setOffscreenPageLimit(adapter.getCount());
        mViewPager.setAdapter(adapter);

        mTabLayout.setupWithViewPager(mViewPager);

    }


    private List<FragmentInfo> initFragments() {

        List<FragmentInfo> mFragments = new ArrayList<>(4);

        mFragments.add(new FragmentInfo("Downloading", DownloadingFragment.class));
        mFragments.add(new FragmentInfo("Downloaded", DownloadedFragment.class));
//        mFragments.add(new FragmentInfo("Finished", InstalledAppFragment.class));

        return mFragments;

    }
}
