package us.bojie.appstorebo.data;

import io.reactivex.Observable;
import us.bojie.appstorebo.bean.AppInfo;
import us.bojie.appstorebo.bean.BaseBean;
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

    public Observable<BaseBean<PageBean<AppInfo>>> getApps() {

//        mApiService.getApps("{\"page\":0}").enqueue(callback);

        return mApiService.getApps("{\"page\":0}");
    }
}
