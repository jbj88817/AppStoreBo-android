package us.bojie.appstorebo.presenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import us.bojie.appstorebo.bean.AppInfo;
import us.bojie.appstorebo.bean.PageBean;
import us.bojie.appstorebo.data.RecommendModel;
import us.bojie.appstorebo.presenter.contract.RecommendContract;

/**
 * Created by bojiejiang on 4/23/17.
 */

public class RecommendPresenter implements RecommendContract.Presenter {

    private RecommendContract.View mView;
    private RecommendModel mModel;

    public RecommendPresenter(RecommendContract.View view, RecommendModel model) {
        mView = view;
        mModel = model;
    }

    @Override
    public void requestData() {
        mView.showLoading();
        mModel.getApps(new Callback<PageBean<AppInfo>>() {
            @Override
            public void onResponse(Call<PageBean<AppInfo>> call, Response<PageBean<AppInfo>> response) {
                if (response != null) {
                    mView.showResult(response.body().getDatas());
                } else {
                    mView.showNoData();
                }

                mView.dismissLoading();
            }

            @Override
            public void onFailure(Call<PageBean<AppInfo>> call, Throwable t) {
                mView.dismissLoading();
                mView.showError(t.getMessage());
            }
        });
    }
}
