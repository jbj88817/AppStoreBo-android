package us.bojie.appstorebo.presenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import us.bojie.appstorebo.common.rx.RxSchedulers;
import us.bojie.appstorebo.common.rx.subscriber.ProgressSubscriber;
import us.bojie.appstorebo.presenter.contract.AppManagerContract;
import zlc.season.rxdownload2.entity.DownloadFlag;
import zlc.season.rxdownload2.entity.DownloadRecord;

/**
 * Created by bojiejiang on 5/27/17.
 */

public class AppManagerPresenter extends BasePresenter<AppManagerContract.IAppMangerModel, AppManagerContract.AppManagerView>{

    @Inject
    public AppManagerPresenter(AppManagerContract.IAppMangerModel model, AppManagerContract.AppManagerView view) {
        super(model, view);
    }

    public void getDownloadingApps() {

        mModel.getDownloadRecord()
                .compose(RxSchedulers.<List<DownloadRecord>>io_main())
                .subscribe(new ProgressSubscriber<List<DownloadRecord>>(mContext, mView) {
                    @Override
                    public void onNext(List<DownloadRecord> records) {
                        mView.showDownloading(records);
                    }
                });
    }

    private List<DownloadRecord> downloadRecordFilter(List<DownloadRecord> downloadRecords) {
        List<DownloadRecord> newList = new ArrayList<>();
        for (DownloadRecord record : downloadRecords) {
            if (record.getFlag() != DownloadFlag.COMPLETED) {
                newList.add(record);
            }
        }
        return newList;
    } 
}
