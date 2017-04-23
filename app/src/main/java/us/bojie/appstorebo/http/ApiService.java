package us.bojie.appstorebo.http;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import us.bojie.appstorebo.bean.AppInfo;
import us.bojie.appstorebo.bean.PageBean;

/**
 * Created by bojiejiang on 4/23/17.
 */

public interface ApiService {

    String BASE_URL = "http://112.124.22.238:8081/course_api/cniaoplay/";
    String BASEIMGURL = "http://file.market.xiaomi.com/mfc/thumbnail/png/w150q80/";

    @GET("featured")
    public Call<PageBean<AppInfo>> getApps(@Query("p") String jsonParam);
}
