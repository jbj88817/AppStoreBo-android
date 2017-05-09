package us.bojie.appstorebo.ui.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;

import butterknife.BindView;
import us.bojie.appstorebo.R;
import us.bojie.appstorebo.bean.Category;
import us.bojie.appstorebo.common.Constant;
import us.bojie.appstorebo.di.component.AppComponent;
import us.bojie.appstorebo.ui.adapter.CategoryAppViewPageAdapter;


public class CategoryAppActivity extends BaseActivity {

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    private Category mCategory;

    @Override
    public int setLayout() {
        return R.layout.activity_cateogry_app;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void init() {
        getData();
        initTabLayout();
    }

    private void initTabLayout() {

        mToolBar.setNavigationIcon(
                new IconicsDrawable(this)
                        .icon(Ionicons.Icon.ion_ios_arrow_back)
                        .sizeDp(16)
                        .color(getResources().getColor(R.color.md_white_1000))
        );

        CategoryAppViewPageAdapter adapter = new CategoryAppViewPageAdapter(getSupportFragmentManager(), mCategory.getId());
        mViewPager.setOffscreenPageLimit(adapter.getCount());
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void getData() {
        Intent intent = getIntent();
        mCategory = (Category) intent.getSerializableExtra(Constant.CATEGORY);
    }

}
