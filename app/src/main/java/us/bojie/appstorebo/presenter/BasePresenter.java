package us.bojie.appstorebo.presenter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import us.bojie.appstorebo.ui.BaseView;

/**
 * Created by bojiejiang on 4/23/17.
 */

public class BasePresenter<M, V extends BaseView> {

    protected M mModel;
    protected V mView;
    protected Context mContext;

    public BasePresenter(M model, V view) {
        mModel = model;
        mView = view;

        initContext();
    }

    private void initContext() {
        if (mView instanceof Fragment) {
            mContext = ((Fragment) mView).getActivity();
        } else {
            mContext = (Activity) mView;
        }
    }

}
