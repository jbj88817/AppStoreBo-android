package us.bojie.appstorebo.ui.widget;

import android.content.Context;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import us.bojie.appstorebo.bean.AppDownloadInfo;
import us.bojie.appstorebo.bean.AppInfo;
import us.bojie.appstorebo.common.util.AppUtils;
import zlc.season.rxdownload2.RxDownload;
import zlc.season.rxdownload2.entity.DownloadEvent;
import zlc.season.rxdownload2.entity.DownloadFlag;

/**
 * Created by bojiejiang on 5/21/17.
 */

public class DownloadButtonController {

    private String mDownloadDir;
    private RxDownload mDownload;


    public void handClick(final DownloadProgressButton btn, final AppInfo appInfo) {

        isAppInstalled(btn.getContext(), appInfo)
                .flatMap(new Function<DownloadEvent, ObservableSource<DownloadEvent>>() {
                    @Override
                    public ObservableSource<DownloadEvent> apply(@NonNull DownloadEvent event) throws Exception {
                        if (event.getFlag() == DownloadFlag.NOT_INSTALL) {
                            return isApkFileExist(appInfo);
                        }
                        return Observable.just(event);
                    }
                }).flatMap(new Function<DownloadEvent, ObservableSource<DownloadEvent>>() {
            @Override
            public ObservableSource<DownloadEvent> apply(@NonNull DownloadEvent event) throws Exception {
                if (event.getFlag() == DownloadFlag.FILE_EXIST) {
                    return getAppDownloadInfo(appInfo).flatMap(new Function<AppDownloadInfo, ObservableSource<DownloadEvent>>() {
                        @Override
                        public ObservableSource<DownloadEvent> apply(@NonNull AppDownloadInfo info) throws Exception {
                            return receiveDownloadStatus(info);
                        }
                    });
                }
                return Observable.just(event);
            }
        }).subscribe(new Consumer<DownloadEvent>() {
            @Override
            public void accept(@NonNull DownloadEvent event) throws Exception {
                int flag = event.getFlag();
                switch (flag) {
                    case DownloadFlag.NORMAL:
                        btn.download();
                        break;
                    case DownloadFlag.INSTALLED:
                        btn.setText("RUN");
                        break;
                    case DownloadFlag.STARTED:
                        btn.setProgress((int) event.getDownloadStatus().getPercentNumber());
                        break;
                    case DownloadFlag.PAUSED:
                        btn.paused();
                        break;
                }
            }
        });

    }

    public Observable<DownloadEvent> isAppInstalled(Context context, AppInfo appInfo) {
        DownloadEvent event = new DownloadEvent();
        event.setFlag(AppUtils.isInstalled(context,
                appInfo.getPackageName()) ? DownloadFlag.INSTALLED : DownloadFlag.NOT_INSTALL);
        return Observable.just(event);
    }

    public Observable<DownloadEvent> isApkFileExist(AppInfo appInfo) {
        String path = mDownloadDir + File.separator + appInfo.getReleaseKeyHash();
        File file = new File(path);

        DownloadEvent event = new DownloadEvent();
        event.setFlag(file.exists() ? DownloadFlag.FILE_EXIST : DownloadFlag.NORMAL);

        return Observable.just(event);
    }

    public Observable<AppDownloadInfo> getAppDownloadInfo(AppInfo appInfo) {
        return null;
    }

    public Observable<DownloadEvent> receiveDownloadStatus(AppDownloadInfo appDownloadInfo) {
        return mDownload.receiveDownloadStatus(appDownloadInfo.getDownloadUrl());
    }
}
