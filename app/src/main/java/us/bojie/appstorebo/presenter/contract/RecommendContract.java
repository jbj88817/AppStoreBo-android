package us.bojie.appstorebo.presenter.contract;

import us.bojie.appstorebo.bean.IndexBean;
import us.bojie.appstorebo.ui.BaseView;

/**
 * Created by bojiejiang on 4/23/17.
 */

public interface RecommendContract {

    interface View extends BaseView {

        void showResult(IndexBean indexBean);
        void onRequestPermissionSuccess();
        void onRequestPermissionError();
    }

//    interface Presenter extends BasePresenter {
//        void requestData();
//    }
}
