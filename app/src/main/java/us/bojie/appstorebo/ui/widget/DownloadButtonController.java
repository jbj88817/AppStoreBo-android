package us.bojie.appstorebo.ui.widget;

import android.content.Context;

import com.jakewharton.rxbinding2.view.RxView;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import retrofit2.http.GET;
import retrofit2.http.Path;
import us.bojie.appstorebo.R;
import us.bojie.appstorebo.bean.AppDownloadInfo;
import us.bojie.appstorebo.bean.AppInfo;
import us.bojie.appstorebo.bean.BaseBean;
import us.bojie.appstorebo.common.Constant;
import us.bojie.appstorebo.common.rx.RxHttpReponseCompat;
import us.bojie.appstorebo.common.rx.RxSchedulers;
import us.bojie.appstorebo.common.util.ACache;
import us.bojie.appstorebo.common.util.AppUtils;
import us.bojie.appstorebo.common.util.PermissionUtil;
import zlc.season.rxdownload2.RxDownload;
import zlc.season.rxdownload2.entity.DownloadEvent;
import zlc.season.rxdownload2.entity.DownloadFlag;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

/**
 * Created by bojiejiang on 5/21/17.
 */

public class DownloadButtonController {

//    private String mDownloadDir;
    private RxDownload mDownload;
    private Api mApi;

    public DownloadButtonController(RxDownload download) {
        mDownload = download;

        if (mDownload != null) {
            mApi = mDownload.getRetrofit().create(Api.class);
        }
    }

    public void handClick(final DownloadProgressButton btn, String url) {

    }

    public void handClick(final DownloadProgressButton btn, final AppInfo appInfo) {

        if (mApi == null) {
            return;
        }

        isAppInstalled(btn.getContext(), appInfo)
                .flatMap(new Function<DownloadEvent, ObservableSource<DownloadEvent>>() {
                    @Override
                    public ObservableSource<DownloadEvent> apply(@NonNull DownloadEvent event) throws Exception {
                        if (event.getFlag() == DownloadFlag.NOT_INSTALL) {
                            return isApkFileExist(btn.getContext(), appInfo);
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
                            appInfo.setAppDownloadInfo(info);
                            return receiveDownloadStatus(info);
                        }
                    });
                }
                return Observable.just(event);
            }
        })
                .compose(RxSchedulers.<DownloadEvent>io_main())
                .subscribe(new DownloadConsumer(btn, appInfo));

    }

    private void bindClick(final DownloadProgressButton btn, final AppInfo appInfo) {
        RxView.clicks(btn).subscribe(new Consumer<Object>() {
            @Override
            public void accept(@NonNull Object o) throws Exception {
                int flag = (int) btn.getTag(R.id.tag_apk_flag);

                switch (flag) {
                    case DownloadFlag.NORMAL:
                    case DownloadFlag.PAUSED:
                        startDownload(btn, appInfo);
                        break;
                    case DownloadFlag.INSTALLED:
                        runApp(btn.getContext(), appInfo);
                        break;
                    case DownloadFlag.STARTED:
                        pausedDownload(appInfo);
                        break;
                    case DownloadFlag.COMPLETED:
                        installApp(btn.getContext(), appInfo);
                        break;
                    //TODO update
                }
            }
        });
    }

    private void installApp(Context context, AppInfo appInfo) {
//        mDownload.getRealFiles()
        String path = ACache.get(context).getAsString(Constant.APK_DOWNLOAD_DIR) + File.separator + appInfo.getReleaseKeyHash();
        AppUtils.installApk(context, path);
    }

    private void startDownload(final DownloadProgressButton btn, final AppInfo appInfo) {

        PermissionUtil.requestPermisson(btn.getContext(), WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(@NonNull Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            AppDownloadInfo downloadInfo = appInfo.getAppDownloadInfo();
                            if (downloadInfo == null) {
                                getAppDownloadInfo(appInfo).subscribe(new Consumer<AppDownloadInfo>() {
                                    @Override
                                    public void accept(@NonNull AppDownloadInfo info) throws Exception {
                                        appInfo.setAppDownloadInfo(info);
                                        download(btn, info, appInfo);
                                    }
                                });
                            } else {
                                download(btn, downloadInfo, appInfo);
                            }
                        }
                    }
                });
    }

    private void download(DownloadProgressButton btn, AppDownloadInfo info, AppInfo appInfo) {
        mDownload.serviceDownload(info.getDownloadUrl(), appInfo.getReleaseKeyHash()).subscribe();
        mDownload.receiveDownloadStatus(info.getDownloadUrl()).subscribe(new DownloadConsumer(btn, appInfo));
    }

    private void pausedDownload(AppInfo appInfo) {
        AppDownloadInfo downloadInfo = appInfo.getAppDownloadInfo();
        mDownload.pauseServiceDownload(downloadInfo.getDownloadUrl()).subscribe();
    }

    private void runApp(Context context, AppInfo info) {
        AppUtils.runApp(context, info.getPackageName());
    }

    public Observable<DownloadEvent> isAppInstalled(Context context, AppInfo appInfo) {
        DownloadEvent event = new DownloadEvent();
        event.setFlag(AppUtils.isInstalled(context,
                appInfo.getPackageName()) ? DownloadFlag.INSTALLED : DownloadFlag.NOT_INSTALL);
        return Observable.just(event);
    }

    public Observable<DownloadEvent> isApkFileExist(Context context, AppInfo appInfo) {
        String path = ACache.get(context).getAsString(Constant.APK_DOWNLOAD_DIR) + File.separator + appInfo.getReleaseKeyHash();
        File file = new File(path);

        DownloadEvent event = new DownloadEvent();
        event.setFlag(file.exists() ? DownloadFlag.FILE_EXIST : DownloadFlag.NORMAL);

        return Observable.just(event);
    }

    public Observable<AppDownloadInfo> getAppDownloadInfo(AppInfo appInfo) {
        return mApi.getAppDownloadInfo(appInfo.getId()).compose(RxHttpReponseCompat.<AppDownloadInfo>compatResult());
    }

    public Observable<DownloadEvent> receiveDownloadStatus(AppDownloadInfo appDownloadInfo) {
        return mDownload.receiveDownloadStatus(appDownloadInfo.getDownloadUrl());
    }

    class DownloadConsumer implements Consumer<DownloadEvent> {

        private DownloadProgressButton btn;
        private AppInfo mAppInfo;

        public DownloadConsumer(DownloadProgressButton btn, AppInfo appInfo) {
            this.btn = btn;
            mAppInfo = appInfo;
        }

        @Override
        public void accept(@NonNull DownloadEvent event) throws Exception {
            int flag = event.getFlag();
            btn.setTag(R.id.tag_apk_flag, flag);
            bindClick(btn, mAppInfo);

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
                    btn.setProgress((int) event.getDownloadStatus().getPercentNumber());
                    btn.paused();
                    break;
                case DownloadFlag.COMPLETED:
                    btn.setText("Install");
//                    installApp(btn.getContext(), mAppInfo);
                    break;
                case DownloadFlag.FAILED:
                    btn.setText("Failed");
                    break;
                case DownloadFlag.DELETED:
                    break;
                //TODO update
            }
        }
    }

    interface Api {
        @GET("download/{id}")
        Observable<BaseBean<AppDownloadInfo>> getAppDownloadInfo(@Path("id") int id);
    }
}
