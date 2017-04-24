package us.bojie.appstorebo.presenter.contract;

import java.util.List;

import us.bojie.appstorebo.bean.AppInfo;
import us.bojie.appstorebo.presenter.BasePresenter;
import us.bojie.appstorebo.ui.BaseView;

/**
 * Created by bojiejiang on 4/23/17.
 */

public interface RecommendContract {

    interface View extends BaseView {

        void showResult(List<AppInfo> data);
        void showNoData();
        void showError(String msg);
    }

    interface Presenter extends BasePresenter {
        void requestData();
    }
}
