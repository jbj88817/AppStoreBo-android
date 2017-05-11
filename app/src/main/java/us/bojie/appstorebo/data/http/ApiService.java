package us.bojie.appstorebo.data.http;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import us.bojie.appstorebo.bean.AppInfo;
import us.bojie.appstorebo.bean.BaseBean;
import us.bojie.appstorebo.bean.Category;
import us.bojie.appstorebo.bean.IndexBean;
import us.bojie.appstorebo.bean.LoginBean;
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

    @GET("index")
    Observable<BaseBean<IndexBean>> index();

    @GET("toplist")
    Observable<BaseBean<PageBean<AppInfo>>> toplist(@Query("page") int page);

    @GET("game")
    Observable<BaseBean<PageBean<AppInfo>>> games(@Query("page") int page);

    @POST("login")
    Observable<BaseBean<LoginBean>> login(@Body LoginRequestBean param);

    @GET("category")
    Observable<BaseBean<List<Category>>> getCategories();

    @GET("category/featured/{categoryid}")
    Observable<BaseBean<PageBean<AppInfo>>> getFeaturedAppsByCategory(@Path("categoryid") int categoryid, @Query("page") int page);

    @GET("category/toplist/{categoryid}")
    Observable<BaseBean<PageBean<AppInfo>>> getTopListAppsByCategory(@Path("categoryid") int categoryid, @Query("page") int page);

    @GET("category/newlist/{categoryid}")
    Observable<BaseBean<PageBean<AppInfo>>> getNewListAppsByCategory(@Path("categoryid") int categoryid, @Query("page") int page);

    @GET("app/{id}")
    Observable<BaseBean<AppInfo>> getAppDetail(@Path("id") int id);

}
