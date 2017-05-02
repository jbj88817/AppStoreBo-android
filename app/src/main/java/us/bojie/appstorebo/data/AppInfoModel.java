package us.bojie.appstorebo.data;

import io.reactivex.Observable;
import us.bojie.appstorebo.bean.AppInfo;
import us.bojie.appstorebo.bean.BaseBean;
import us.bojie.appstorebo.bean.IndexBean;
import us.bojie.appstorebo.bean.PageBean;
import us.bojie.appstorebo.data.http.ApiService;

/**
 * Created by bojiejiang on 4/23/17.
 */

public class AppInfoModel {

    private ApiService mApiService;

    public AppInfoModel(ApiService apiService) {
        mApiService = apiService;
    }


    public Observable<BaseBean<IndexBean>> index() {
        return mApiService.index();
    }

    public Observable<BaseBean<PageBean<AppInfo>>> toplist(int page) {
        return mApiService.toplist(page);
    }

    public Observable<BaseBean<PageBean<AppInfo>>> games(int page) {
        return mApiService.games(page);
    }
}
