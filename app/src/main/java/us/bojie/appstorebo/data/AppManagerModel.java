package us.bojie.appstorebo.data;

import java.util.List;

import io.reactivex.Observable;
import us.bojie.appstorebo.presenter.contract.AppManagerContract;
import zlc.season.rxdownload2.RxDownload;
import zlc.season.rxdownload2.entity.DownloadRecord;

/**
 * Created by bojiejiang on 5/27/17.
 */

public class AppManagerModel implements AppManagerContract.IAppMangerModel {


    private RxDownload mRxDownload;

    public AppManagerModel(RxDownload rxDownload) {
        mRxDownload = rxDownload;
    }

    @Override
    public Observable<List<DownloadRecord>> getDownloadRecord() {
        return mRxDownload.getTotalDownloadRecords();
    }
}
