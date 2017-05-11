package us.bojie.appstorebo.presenter.contract;

import us.bojie.appstorebo.bean.AppInfo;
import us.bojie.appstorebo.bean.IndexBean;
import us.bojie.appstorebo.bean.PageBean;
import us.bojie.appstorebo.ui.BaseView;

/**
 * Created by bojiejiang on 4/23/17.
 */

public interface AppInfoContract {

    interface View extends BaseView {

        void showResult(IndexBean indexBean);
        void onRequestPermissionSuccess();
        void onRequestPermissionError();
    }

    interface AppInfoView extends BaseView {
        void showResult(PageBean<AppInfo> appInfoPageBean);
        void onLoadMoreCompleted();
    }

    interface AppDetailView extends BaseView {
        void showAppDetail(AppInfo appInfo);
    }
}
