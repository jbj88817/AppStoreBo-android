package us.bojie.appstorebo.bean;

import java.io.Serializable;

/**
 * Created by bojiejiang on 4/25/17.
 */

public class BaseBean<T> implements Serializable {
    private int status;
    private String message;
    private T data;

    public static final int SUCCESS = 1;

    public boolean isSucessful() {
        return status == SUCCESS;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
