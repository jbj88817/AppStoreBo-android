package us.bojie.appstorebo.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;

import java.util.List;

import butterknife.BindView;
import us.bojie.appstorebo.R;
import us.bojie.appstorebo.bean.Category;
import us.bojie.appstorebo.common.Constant;
import us.bojie.appstorebo.di.component.AppComponent;
import us.bojie.appstorebo.di.component.DaggerCategoryComponent;
import us.bojie.appstorebo.di.module.CategoryModule;
import us.bojie.appstorebo.presenter.CateogoryPresenter;
import us.bojie.appstorebo.presenter.contract.CategoryContract;
import us.bojie.appstorebo.ui.activity.CategoryAppActivity;
import us.bojie.appstorebo.ui.adapter.CategoryAdapter;

public class CategoryFragment extends ProgressFragment<CateogoryPresenter> implements CategoryContract.CategoryView {

    @BindView(R.id.recycle_view)
    RecyclerView mRecycleView;

    private CategoryAdapter mAdapter;

    @Override
    public int setLayout() {
        return R.layout.template_recycler_view;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerCategoryComponent.builder()
                .appComponent(appComponent)
                .categoryModule(new CategoryModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void init() {
        initRecyclerView();
        mPresenter.getAllCategory();
    }

    private void initRecyclerView() {
        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        mRecycleView.addItemDecoration(itemDecoration);

        mAdapter = new CategoryAdapter();
        mRecycleView.setAdapter(mAdapter);
        mRecycleView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), CategoryAppActivity.class);
                intent.putExtra(Constant.CATEGORY, mAdapter.getData().get(position));
                startActivity(intent);
            }
        });
    }

    @Override
    public void showData(List<Category> categories) {
        mAdapter.addData(categories);
    }
}
