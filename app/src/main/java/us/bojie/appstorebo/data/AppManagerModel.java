package us.bojie.appstorebo.data;

import android.content.Context;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import us.bojie.appstorebo.common.Constant;
import us.bojie.appstorebo.common.apkparser.AndroidApk;
import us.bojie.appstorebo.common.util.ACache;
import us.bojie.appstorebo.presenter.contract.AppManagerContract;
import zlc.season.rxdownload2.RxDownload;
import zlc.season.rxdownload2.entity.DownloadRecord;

/**
 * Created by bojiejiang on 5/27/17.
 */

public class AppManagerModel implements AppManagerContract.IAppMangerModel {


    private Context mContext;
    private RxDownload mRxDownload;

    public AppManagerModel(Context context, RxDownload rxDownload) {
        mRxDownload = rxDownload;
        mContext = context;
    }

    @Override
    public Observable<List<DownloadRecord>> getDownloadRecord() {
        return mRxDownload.getTotalDownloadRecords();
    }

    @Override
    public RxDownload getRxDownload() {
        return mRxDownload;
    }

    @Override
    public Observable<List<AndroidApk>> getLocalAPKs() {
        final String dir = ACache.get(mContext).getAsString(Constant.APK_DOWNLOAD_DIR);
        return Observable.create(new ObservableOnSubscribe<List<AndroidApk>>() {
            @Override
            public void subscribe(ObservableEmitter<List<AndroidApk>> e) throws Exception {
                e.onNext(scanApks(dir));
                e.onComplete();
            }
        });
    }

    private List<AndroidApk> scanApks(String dir) {
        File file = new File(dir);
        if (!file.isDirectory()) {
            throw new RuntimeException("is not a dir");
        }

        File[] apks = file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                if (pathname.isDirectory()) {
                    return false;
                }
                return pathname.getName().endsWith(".apk");
            }
        });

        List<AndroidApk> androidApks = new ArrayList<>();

        for (File apk : apks) {
            AndroidApk androidApk = AndroidApk.read(mContext, apk.getPath());
            androidApks.add(androidApk);
        }

        return androidApks;

    }
}
