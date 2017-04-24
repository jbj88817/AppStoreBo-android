package us.bojie.appstorebo.data;

import retrofit2.Callback;
import us.bojie.appstorebo.bean.AppInfo;
import us.bojie.appstorebo.bean.PageBean;
import us.bojie.appstorebo.http.ApiService;
import us.bojie.appstorebo.http.HttpManager;

/**
 * Created by bojiejiang on 4/23/17.
 */

public class RecommendModel {

    public void getApps(Callback<PageBean<AppInfo>> callback) {
        HttpManager manager = new HttpManager();
        ApiService apiService = manager.getRetrofit(manager.getOkHttpClient()).create(ApiService.class);

        apiService.getApps("{\"page\":0}").enqueue(callback);
    }
}
