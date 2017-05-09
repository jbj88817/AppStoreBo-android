package us.bojie.appstorebo.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import us.bojie.appstorebo.ui.fragment.CategoryAppFragment;

public class CategoryAppViewPageAdapter extends FragmentStatePagerAdapter {

    private List<String> titles = new ArrayList<>(3);
    private int mCategoryId;

    public CategoryAppViewPageAdapter(FragmentManager fm, int categoryId) {
        super(fm);
        mCategoryId = categoryId;

        titles.add("Featured");
        titles.add("TopList");
        titles.add("NewList");

    }

    @Override
    public Fragment getItem(int position) {
        return CategoryAppFragment.newInstance(mCategoryId, position);
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
