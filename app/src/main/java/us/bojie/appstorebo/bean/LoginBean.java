package us.bojie.appstorebo.bean;

/**
 * Created by bojiejiang on 5/2/17.
 */

public class LoginBean extends BaseEntity{

    private String token;
    private User user;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
