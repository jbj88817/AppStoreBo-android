package us.bojie.appstorebo.data;

import retrofit2.Callback;
import us.bojie.appstorebo.bean.AppInfo;
import us.bojie.appstorebo.bean.PageBean;
import us.bojie.appstorebo.data.http.ApiService;

/**
 * Created by bojiejiang on 4/23/17.
 */

public class RecommendModel {

    private ApiService mApiService;

    public RecommendModel(ApiService apiService) {
        mApiService = apiService;
    }

    public void getApps(Callback<PageBean<AppInfo>> callback) {

        mApiService.getApps("{\"page\":0}").enqueue(callback);
    }
}
