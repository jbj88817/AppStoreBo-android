package us.bojie.appstorebo.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import us.bojie.appstorebo.R;
import us.bojie.appstorebo.bean.AppInfo;
import us.bojie.appstorebo.bean.PageBean;
import us.bojie.appstorebo.http.ApiService;
import us.bojie.appstorebo.http.HttpManager;
import us.bojie.appstorebo.ui.adapter.RecommendAppAdapter;


public class RecommendFragment extends Fragment {

    @BindView(R.id.recycle_view)
    RecyclerView mRecycleView;
    Unbinder unbinder;

    private RecommendAppAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommend, container, false);
        unbinder = ButterKnife.bind(this, view);
        initData();
        return view;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void initData() {
        HttpManager manager = new HttpManager();
        ApiService apiService = manager.getRetrofit(manager.getOkHttpClient()).create(ApiService.class);

        apiService.getApps("{\"page\":0}").enqueue(new Callback<PageBean<AppInfo>>() {
            @Override
            public void onResponse(Call<PageBean<AppInfo>> call, Response<PageBean<AppInfo>> response) {
                PageBean<AppInfo> pageBean = response.body();
                List<AppInfo> data = pageBean.getDatas();
                initRecycleView(data);
            }

            @Override
            public void onFailure(Call<PageBean<AppInfo>> call, Throwable t) {

            }
        });
    }

    private void initRecycleView(List<AppInfo> data) {
        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycleView.setItemAnimator(new DefaultItemAnimator());

        // Set line between each item
        mRecycleView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        mAdapter = new RecommendAppAdapter(getActivity(), data);
        mRecycleView.setAdapter(mAdapter);

    }
}
