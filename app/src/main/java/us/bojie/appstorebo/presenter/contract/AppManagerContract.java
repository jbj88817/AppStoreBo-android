package us.bojie.appstorebo.presenter.contract;

import java.util.List;

import io.reactivex.Observable;
import us.bojie.appstorebo.common.apkparser.AndroidApk;
import us.bojie.appstorebo.ui.BaseView;
import zlc.season.rxdownload2.RxDownload;
import zlc.season.rxdownload2.entity.DownloadRecord;

/**
 * Created by bojiejiang on 4/23/17.
 */

public interface AppManagerContract {

    interface AppManagerView extends BaseView {
        void showDownloading(List<DownloadRecord> records);
        void showApps(List<AndroidApk> apps);
    }

    interface IAppMangerModel {
        Observable<List<DownloadRecord>> getDownloadRecord();
        RxDownload getRxDownload();

        Observable<List<AndroidApk>> getLocalAPKs();
        Observable<List<AndroidApk>> getInstalledApps();
    }
}
