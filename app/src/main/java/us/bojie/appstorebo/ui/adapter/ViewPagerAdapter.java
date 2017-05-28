package us.bojie.appstorebo.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import us.bojie.appstorebo.ui.bean.FragmentInfo;

/**
 * Created by bojiejiang on 4/21/17.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<FragmentInfo> mFragments = new ArrayList<>(4);

    public ViewPagerAdapter(FragmentManager fm, List<FragmentInfo> fragments) {
        super(fm);
//        initFragment();
        mFragments = fragments;
    }


    @Override
    public Fragment getItem(int position) {
//        Fragment fragment = null;
//        switch (position) {
//            case 0:
//                fragment = new RecommendFragment();
//                break;
//            case 1:
//                fragment = new RankingFragment();
//                break;
//            case 2:
//                fragment = new GamesFragment();
//                break;
//            case 3:
//                fragment = new CategoryFragment();
//                break;
//        }
//        return fragment;

        try {
            return (Fragment) mFragments.get(position).getFragment().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragments.get(position).getTitle();
    }
}
