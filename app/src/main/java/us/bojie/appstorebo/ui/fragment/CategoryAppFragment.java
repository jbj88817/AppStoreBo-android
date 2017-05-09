package us.bojie.appstorebo.ui.fragment;

import us.bojie.appstorebo.di.component.AppComponent;
import us.bojie.appstorebo.ui.adapter.AppInfoAdapter;

/**
 * Created by bojiejiang on 5/8/17.
 */

public class CategoryAppFragment extends BaseAppInfoFragment {

    public static final int FEATURED = 0;
    public static final int TOPLIST = 1;
    public static final int NEWLIST = 2;

    private int categoryId;
    private int fragmentType;

    private CategoryAppFragment(int categoryId, int fragmentType) {
        this.categoryId = categoryId;
        this.fragmentType = fragmentType;
    }

    public static CategoryAppFragment newInstance(int categoryId, int fragmentType) {
        return new CategoryAppFragment(categoryId, fragmentType);
    }

    @Override
    AppInfoAdapter buildAdapter() {
        return null;
    }

    @Override
    int type() {
        return 0;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void init() {
//        initRecyclerView();
    }
}
