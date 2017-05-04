package us.bojie.appstorebo.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;
import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.BindView;
import io.reactivex.functions.Consumer;
import us.bojie.appstorebo.R;
import us.bojie.appstorebo.bean.User;
import us.bojie.appstorebo.common.font.BojieFont;
import us.bojie.appstorebo.di.component.AppComponent;
import us.bojie.appstorebo.ui.adapter.ViewPagerAdapter;

public class MainActivity extends BaseActivity {

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.navigation_view)
    NavigationView mNavigationView;
    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    private View headerView;
    private ImageView mUserHeadView;
    private TextView mTextUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void init() {

        RxBus.get().register(this);

        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.READ_PHONE_STATE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            initDrawerLayout();
                            initTabLayout();
                        }
                    }
                });
    }

    private void initDrawerLayout() {
        headerView = mNavigationView.getHeaderView(0);
        mUserHeadView = (ImageView) headerView.findViewById(R.id.img_avatar);
        mUserHeadView.setImageDrawable(new IconicsDrawable(this, BojieFont.Icon.head).colorRes(R.color.white));

        mTextUserName = (TextView) headerView.findViewById(R.id.txt_username);

        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });

        mNavigationView.getMenu().findItem(R.id.menu_app_update).setIcon(new IconicsDrawable(this, Ionicons.Icon.ion_ios_loop));
        mNavigationView.getMenu().findItem(R.id.menu_download_manager).setIcon(new IconicsDrawable(this, BojieFont.Icon.download));
        mNavigationView.getMenu().findItem(R.id.menu_app_uninstall).setIcon(new IconicsDrawable(this, Ionicons.Icon.ion_ios_trash_outline));
        mNavigationView.getMenu().findItem(R.id.menu_setting).setIcon(new IconicsDrawable(this, Ionicons.Icon.ion_ios_gear_outline));

        mNavigationView.getMenu().findItem(R.id.menu_logout).setIcon(new IconicsDrawable(this, BojieFont.Icon.shutdown));


        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.menu_logout:
//                        logout();
                        break;

                }
                return false;
            }
        });


        mToolBar.inflateMenu(R.menu.toolbar_menu);

        ActionBarDrawerToggle drawerToggle =
                new ActionBarDrawerToggle(this, mDrawerLayout, mToolBar, R.string.open, R.string.close);

        drawerToggle.syncState();

        mDrawerLayout.addDrawerListener(drawerToggle);
    }

    private void initTabLayout() {

        PagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Subscribe
    public void getUser(User user) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unregister(this);
    }
}
