package us.bojie.appstorebo.bean;

/**
 * Created by bojiejiang on 4/30/17.
 */

public class Banner {


    /**
     * thumbnail : http://t5.market.mi-img.com/thumbnail/jpeg/l750/AppStore/09d3864948d6841582ad8b52aca9ce19575cd7d11
     * action : app
     * id : com.xinlukou.metroman
     */

    private String thumbnail;
    private String action;
    private String id;

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
