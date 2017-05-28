package us.bojie.appstorebo.presenter.contract;

import java.util.List;

import io.reactivex.Observable;
import us.bojie.appstorebo.ui.BaseView;
import zlc.season.rxdownload2.entity.DownloadRecord;

/**
 * Created by bojiejiang on 4/23/17.
 */

public interface AppManagerContract {

    interface AppManagerView extends BaseView {
        void showDownloading(List<DownloadRecord> records);
    }

    interface IAppMangerModel {
        Observable<List<DownloadRecord>> getDownloadRecord();
    }
}
