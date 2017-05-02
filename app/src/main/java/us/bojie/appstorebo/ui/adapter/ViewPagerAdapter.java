package us.bojie.appstorebo.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import us.bojie.appstorebo.ui.bean.FragmentInfo;
import us.bojie.appstorebo.ui.fragment.CategoryFragment;
import us.bojie.appstorebo.ui.fragment.GamesFragment;
import us.bojie.appstorebo.ui.fragment.TopListFragment;
import us.bojie.appstorebo.ui.fragment.RecommendFragment;

/**
 * Created by bojiejiang on 4/21/17.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<FragmentInfo> mFragments = new ArrayList<>(4);

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        initFragment();
    }

    private void initFragment() {
        mFragments.add(new FragmentInfo("Recommend", RecommendFragment.class));
        mFragments.add(new FragmentInfo("Ranking", TopListFragment.class));
        mFragments.add(new FragmentInfo("Games", GamesFragment.class));
        mFragments.add(new FragmentInfo("Category", CategoryFragment.class));
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
