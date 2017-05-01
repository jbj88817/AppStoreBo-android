package us.bojie.appstorebo.data.http;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import us.bojie.appstorebo.bean.AppInfo;
import us.bojie.appstorebo.bean.BaseBean;
import us.bojie.appstorebo.bean.PageBean;
import us.bojie.appstorebo.bean.requestbean.LoginRequestBean;

/**
 * Created by bojiejiang on 4/23/17.
 */

public interface ApiService {

    String BASE_URL = "http://112.124.22.238:8081/course_api/cniaoplay/";
    String BASE_IMG_URL = "http://file.market.xiaomi.com/mfc/thumbnail/png/w150q80/";

//    @GET("featured")
//    Call<PageBean<AppInfo>> getApps(@Query("p") String jsonParam);

    @GET("featured2")
    Observable<BaseBean<PageBean<AppInfo>>> getApps(@Query("p") String jsonParam);

    @POST("login")
    Observable<BaseBean> login(@Body LoginRequestBean bean);

}
